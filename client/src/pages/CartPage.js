// CartPage.js
import React from 'react';
import { useQuery } from '@apollo/client';
import { GET_CART } from '../queries/cartQueries'; 
function CartPage() {
  const { loading, error, data } = useQuery(GET_CART);

  if (loading) return <p>Загрузка корзины...</p>;
  if (error) return <p>Ошибка при загрузке корзины.</p>;

  const { cart } = data;

  if (!cart || cart.items.length === 0) {
    return <p>Ваша корзина пуста.</p>;
  }

  const total = cart.items.reduce(
    (sum, item) => sum + item.quantity * item.product.price,
    0
  );

  return (
    <div className="Cart">
      <h2>Ваша корзина</h2>
      <ul>
        {cart.items.map((item) => (
          <li key={item.id}>
            {item.product.name} x {item.quantity} - $
            {(item.product.price * item.quantity).toFixed(2)}
          </li>
        ))}
      </ul>
      <p>
        <strong>Итого: ${total.toFixed(2)}</strong>
      </p>
    </div>
  );
}

export default CartPage;