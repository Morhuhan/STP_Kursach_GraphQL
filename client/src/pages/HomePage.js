import React from 'react';
import Filters from '../components/Filters';
import ProductList from '../components/ProductList';

function HomePage() {
  return (
    <div className="HomePage">
      <Filters />
      <ProductList />
    </div>
  );
}

export default HomePage;