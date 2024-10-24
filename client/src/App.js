import React from 'react';
import ProductList from './ProductList';
import Cart from './Cart';

function App() {
  const cartId = 1; 

  return (
    <div>
      <h1>Онлайн-магазин</h1>
      <ProductList />
      <Cart cartId={cartId} />
    </div>
  );
}

export default App;