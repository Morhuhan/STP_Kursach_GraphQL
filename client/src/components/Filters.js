import React from 'react';
import { useQuery, gql } from '@apollo/client';

const GET_CATEGORIES = gql`
  query GetCategories {
    categories {
      id
      name
    }
  }
`;

function Filters({ filterInputs, onFilterInputChange, onApplyFilters }) {
  const { loading, error, data } = useQuery(GET_CATEGORIES);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    onFilterInputChange({ [name]: value });
  };

  if (loading) return <p>Загрузка категорий...</p>;
  if (error) return <p>Ошибка при загрузке категорий.</p>;

  return (
    <div className="Filters">
      <div className="SearchBar">
        <input
          type="text"
          name="searchTerm"
          placeholder="Поиск товаров..."
          value={filterInputs.searchTerm}
          onChange={handleInputChange}
        />
      </div>
      <div className="PriceFilter">
        <input
          type="number"
          name="minPrice"
          placeholder="Минимальная цена"
          value={filterInputs.minPrice}
          onChange={handleInputChange}
        />
        <input
          type="number"
          name="maxPrice"
          placeholder="Максимальная цена"
          value={filterInputs.maxPrice}
          onChange={handleInputChange}
        />
      </div>
      <div className="CategoryFilter">
        <select
          name="categoryId"
          value={filterInputs.categoryId}
          onChange={handleInputChange}
        >
          <option value="">Все категории</option>
          {data.categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      <button onClick={onApplyFilters}>Применить фильтры</button>
    </div>
  );
}

export default Filters;