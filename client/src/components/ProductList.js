import React from 'react';
import { useQuery, gql, useMutation } from '@apollo/client';
import { Link } from 'react-router-dom';
import noImage from '../noimage.png';

const GET_FILTERED_PRODUCTS = gql`
  query GetFilteredProducts(
    $searchTerm: String
    $minPrice: Float
    $maxPrice: Float
    $categoryId: ID
  ) {
    products(
      searchTerm: $searchTerm
      minPrice: $minPrice
      maxPrice: $maxPrice
      categoryId: $categoryId
    ) {
      id
      name
      price
      imageUrl
    }
  }
`;

const ADD_TO_CART = gql`
  mutation AddToCart($productId: ID!, $quantity: Int!) {
    addToCart(productId: $productId, quantity: $quantity) {
      id
      items {
        id
        quantity
        product {
          id
          name
          price
        }
      }
    }
  }
`;

function ProductList({ filters }) {
  const { loading, error, data } = useQuery(GET_FILTERED_PRODUCTS, {
    variables: {
      searchTerm: filters.searchTerm || null,
      minPrice: filters.minPrice ? parseFloat(filters.minPrice) : null,
      maxPrice: filters.maxPrice ? parseFloat(filters.maxPrice) : null,
      categoryId: filters.categoryId || null,
    },
  });

  const [addToCart] = useMutation(ADD_TO_CART);

  if (loading) return <p>Загрузка товаров...</p>;
  if (error) return <p>Ошибка при загрузке товаров.</p>;

  const handleAddToCart = (productId) => {
    addToCart({
      variables: { productId, quantity: 1 },
      refetchQueries: ['GetCart'],
    });
  };

  return (
    <div className="ProductList">
      {data.products.map((product) => (
        <div key={product.id} className="ProductItem">
          <Link to={`/product/${product.id}`}>
            <img src={product.imageUrl || noImage} alt={product.name} />
          </Link>
          <div>
            <h3>{product.name}</h3>
            <p>${product.price.toFixed(2)}</p>
            <button onClick={() => handleAddToCart(product.id)}>Добавить в корзину</button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ProductList;