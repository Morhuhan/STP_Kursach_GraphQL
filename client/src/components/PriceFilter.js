import React from 'react';

function PriceFilter() {
  return (
    <div className="PriceFilter">
      <input type="number" placeholder="Минимальная цена" />
      <input type="number" placeholder="Максимальная цена" />
    </div>
  );
}

export default PriceFilter;