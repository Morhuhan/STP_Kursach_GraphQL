import { ApolloClient, InMemoryCache, from } from '@apollo/client';
import { createHttpLink } from '@apollo/client';
import { onError } from '@apollo/client/link/error';

export const createApolloClient = () => {
  const httpLink = createHttpLink({
    uri: 'http://localhost:8080/graphql',
    credentials: 'include',
  });

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
    link: from([errorLink, httpLink]),
    cache: new InMemoryCache(),
  });

  return client;
};