import React from 'react';
import SearchBar from './SearchBar';
import PriceFilter from './PriceFilter';

function Filters() {
  return (
    <div className="Filters">
      <SearchBar />
      <PriceFilter />
    </div>
  );
}

export default Filters;