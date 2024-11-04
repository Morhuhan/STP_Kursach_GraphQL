import React, { useState } from 'react';
import Filters from '../components/Filters';
import ProductList from '../components/ProductList';

function HomePage() {
  const [filterInputs, setFilterInputs] = useState({
    searchTerm: '',
    minPrice: '',
    maxPrice: '',
    categoryId: '',
  });

  const [filters, setFilters] = useState({
    searchTerm: '',
    minPrice: '',
    maxPrice: '',
    categoryId: '',
  });

  const handleFilterInputChange = (newInputs) => {
    setFilterInputs((prevInputs) => ({ ...prevInputs, ...newInputs }));
  };

  const applyFilters = () => {
    setFilters({ ...filterInputs });
  };

  return (
    <div className="HomePage">
      <Filters
        filterInputs={filterInputs}
        onFilterInputChange={handleFilterInputChange}
        onApplyFilters={applyFilters}
      />
      <ProductList filters={filters} />
    </div>
  );
}

export default HomePage;