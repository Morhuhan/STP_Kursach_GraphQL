import React, { useState, useEffect } from 'react';
import { useQuery, gql, useMutation, useSubscription } from '@apollo/client';
import { Link } from 'react-router-dom';
import noImage from '../noimage.png';
import { GET_CART } from './Cart'; 

const GET_PAGINATED_PRODUCTS = gql`
  query GetPaginatedProducts(
    $searchTerm: String,
    $minPrice: Float,
    $maxPrice: Float,
    $categoryId: ID,
    $page: Int,
    $pageSize: Int
  ) {
    products(
      searchTerm: $searchTerm,
      minPrice: $minPrice,
      maxPrice: $maxPrice,
      categoryId: $categoryId,
      page: $page,
      pageSize: $pageSize
    ) {
      products {
        id
        name
        price
        imageUrl
        inStock
      }
      totalPages
      currentPage
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

const PRODUCT_UPDATED = gql`
  subscription OnProductUpdated {
    productUpdated {
      id
      name
      price
      imageUrl
      inStock
    }
  }
`;

function ProductList({ filters }) {
  const [paginationVars, setPaginationVars] = useState({
    page: 1,
    pageSize: 10,
  });

  const { loading, error, data, refetch } = useQuery(GET_PAGINATED_PRODUCTS, {
    variables: {
      searchTerm: filters.searchTerm || null,
      minPrice: filters.minPrice ? parseFloat(filters.minPrice) : null,
      maxPrice: filters.maxPrice ? parseFloat(filters.maxPrice) : null,
      categoryId: filters.categoryId || null,
      page: paginationVars.page,
      pageSize: paginationVars.pageSize,
    },
    notifyOnNetworkStatusChange: true,
  });

  const { data: subscriptionData } = useSubscription(PRODUCT_UPDATED);

  useEffect(() => {
    if (subscriptionData) {
      refetch();
    }
  }, [subscriptionData, refetch]);

  const [addToCart] = useMutation(ADD_TO_CART);

  useEffect(() => {
    setPaginationVars((prev) => ({ ...prev, page: 1 }));
  }, [filters]);

  if (loading && !data) return <p>Загрузка товаров...</p>;
  if (error) return <p>Ошибка при загрузке товаров.</p>;

  const products = data.products.products;
  const totalPages = data.products.totalPages;
  const currentPage = data.products.currentPage;

  const handleAddToCart = (productId) => {
    addToCart({
      variables: { productId, quantity: 1 },
      refetchQueries: [{ query: GET_CART }],
    });
  };

  const handlePageChange = (pageNum) => {
    setPaginationVars((prev) => ({ ...prev, page: pageNum }));
    refetch({
      page: pageNum,
    });
  };

  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(
        <button
          key={i}
          onClick={() => handlePageChange(i)}
          disabled={currentPage === i}
        >
          {i}
        </button>
      );
    }
    return pageNumbers;
  };

  return (
    <div className="ProductList">
      {products.map((product) => (
        <div key={product.id} className="ProductItem">
          <Link to={`/product/${product.id}`}>
            <img src={product.imageUrl || noImage} alt={product.name} />
          </Link>
          <div>
            <h3>{product.name}</h3>
            <p>${product.price.toFixed(2)}</p>
            <p>{product.inStock ? 'В наличии' : 'Нет в наличии'}</p>
            <button
              onClick={() => handleAddToCart(product.id)}
              disabled={!product.inStock}
            >
              Добавить в корзину
            </button>
          </div>
        </div>
      ))}
      <div className="Pagination">{renderPageNumbers()}</div>
    </div>
  );
}

export default ProductList;