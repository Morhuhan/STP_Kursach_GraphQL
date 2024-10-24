import React from 'react';
import { useQuery, useMutation, gql } from '@apollo/client';
import GET_CART from './Cart';

const GET_PRODUCTS = gql`
  query GetProducts {
    products {
      id
      name
      price
    }
  }
`;

const ADD_TO_CART = gql`
  mutation AddToCart($cartId: ID!, $productId: ID!, $quantity: Int!) {
    addToCart(cartId: $cartId, productId: $productId, quantity: $quantity) {
      id
      items {
        id
        productId
        quantity
      }
    }
  }
`;

function ProductList() {
  const { loading, error, data } = useQuery(GET_PRODUCTS);
  const [addToCart] = useMutation(ADD_TO_CART);

  const handleAddToCart = (productId) => {
    addToCart({
      variables: { cartId: 1, productId, quantity: 1 }, 
      refetchQueries: [{ query: GET_CART, variables: { id: 1 } }],
    });
  };

  if (loading) return <p>Загрузка...</p>;
  if (error) return <p>Ошибка: {error.message}</p>;

  return (
    <div>
      <h2>Список продуктов</h2>
      <ul>
        {data.products.map((product) => (
          <li key={product.id}>
            {product.name} - ${product.price}{' '}
            <button onClick={() => handleAddToCart(product.id)}>Добавить в корзину</button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default ProductList;