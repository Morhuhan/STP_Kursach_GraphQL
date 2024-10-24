import React from 'react';
import { useQuery, gql } from '@apollo/client';

const GET_CART = gql`
  query GetCart($id: ID!) {
    cart(id: $id) {
      id
      items {
        id
        productId
        quantity
      }
    }
  }
`;

function Cart({ cartId }) {
  const { loading, error, data } = useQuery(GET_CART, {
    variables: { id: cartId },
  });

  if (loading) return <p>Загрузка...</p>;
  if (error) return <p>Ошибка: {error.message}</p>;

  return (
    <div>
      <h2>Корзина</h2>
      <ul>
        {data.cart.items.map((item) => (
          <li key={item.id}>
            Продукт ID: {item.productId}, Количество: {item.quantity}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Cart;