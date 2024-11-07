import { ApolloClient, InMemoryCache, from, split } from '@apollo/client';
import { createHttpLink } from '@apollo/client';
import { onError } from '@apollo/client/link/error';
import { GraphQLWsLink } from '@apollo/client/link/subscriptions';
import { createClient } from 'graphql-ws';
import { getMainDefinition } from '@apollo/client/utilities';

export const createApolloClient = () => {
  const httpLink = createHttpLink({
    uri: 'http://localhost:8080/graphql',
    credentials: 'include',
  });

  const wsLink = new GraphQLWsLink(
    createClient({
      url: 'ws://localhost:8080/graphql',
      connectionParams: {
        authToken: localStorage.getItem('authToken'),
      },
    })
  );

  const splitLink = split(
    ({ query }) => {
      const definition = getMainDefinition(query);
      return (
        definition.kind === 'OperationDefinition' &&
        definition.operation === 'subscription'
      );
    },
    wsLink,
    httpLink
  );

  const errorLink = onError(({ graphQLErrors, networkError }) => {
    if (graphQLErrors) {
      for (let err of graphQLErrors) {
        if (err.extensions.code === 'UNAUTHENTICATED') {
          window.location.href = '/login';
        }
      }
    }

    if (networkError) {
      if (networkError.statusCode === 401) {
        window.location.href = '/login';
      } else if (networkError.name === 'ServerParseError') {
        window.location.href = '/login';
      } else {
        console.error(`[Network error]: ${networkError}`);
      }
    }
  });

  const client = new ApolloClient({
    link: from([errorLink, splitLink]),
    cache: new InMemoryCache({
      typePolicies: {
        Query: {
          fields: {
            product: {
              keyArgs: ['id'],
              merge(existing, incoming) {
                return { ...existing, ...incoming };
              },
            },
          },
        },
      },
    }),
  });

  return client;
};