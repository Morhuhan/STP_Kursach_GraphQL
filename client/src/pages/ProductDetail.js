import React, { useState } from 'react';
import { useQuery, gql, useMutation } from '@apollo/client';
import { useParams } from 'react-router-dom';
import noImage from '../noimage.png';

const GET_PRODUCT_DETAILS = gql`
  query GetProductDetails($id: ID!) {
    product(id: $id) {
      id
      name
      price
      description
      imageUrl
      rating
      reviews {
        id
        author {
          username
        }
        content
        rating
      }
    }
  }
`;

const ADD_REVIEW = gql`
  mutation AddReview($productId: ID!, $authorId: ID!, $content: String!, $rating: Float!) {
  addReview(productId: $productId, authorId: $authorId, content: $content, rating: $rating) {
    id
    author {
      username
    }
    content
    rating
    }
  }
`;

function ProductDetail() {
  const { id } = useParams();
  const { loading, error, data } = useQuery(GET_PRODUCT_DETAILS, {
    variables: { id },
  });

  const [author, setAuthor] = useState('');
  const [content, setContent] = useState('');
  const [ratingInput, setRatingInput] = useState(0);

  const [addReview] = useMutation(ADD_REVIEW, {
    refetchQueries: [{ query: GET_PRODUCT_DETAILS, variables: { id } }],
  });

  const handleAddReview = (e) => {
    e.preventDefault();
    addReview({
      variables: {
        productId: id,
        author,
        content,
        rating: parseFloat(ratingInput),
      },
    });
    setAuthor('');
    setContent('');
    setRatingInput(0);
  };

  if (loading) return <p>Загрузка товара...</p>;
  if (error) return <p>Ошибка при загрузке товара.</p>;

  const { product } = data;

  return (
    <div className="ProductDetail">
      <div className="ProductInfo">
        <img src={product.imageUrl || noImage} alt={product.name} />
        <h2>{product.name}</h2>
        <p>Цена: ${product.price.toFixed(2)}</p>
        <p>Рейтинг: {product.rating}</p>
        <p>Описание: {product.description}</p>
      </div>

      <div className="ProductReviews">
        <h3>Отзывы:</h3>
        {product.reviews.length > 0 ? (
          <ul>
            {product.reviews.map((review) => (
              <li key={review.id}>
                <strong>{review.author.username}</strong> (Рейтинг: {review.rating}): {review.content}
              </li>
            ))}
          </ul>
        ) : (
          <p>Отзывов пока нет.</p>
        )}
      </div>

      <div className="AddReview">
        <h3>Добавить отзыв:</h3>
        <form onSubmit={handleAddReview}>
          <input
            type="text"
            placeholder="Ваше имя"
            value={author}
            onChange={(e) => setAuthor(e.target.value)}
            required
          />
          <textarea
            placeholder="Ваш отзыв"
            value={content}
            onChange={(e) => setContent(e.target.value)}
            required
          />
          <input
            type="number"
            placeholder="Рейтинг (0-5)"
            value={ratingInput}
            onChange={(e) => setRatingInput(e.target.value)}
            min="0"
            max="5"
            step="0.1"
            required
          />
          <button type="submit">Отправить</button>
        </form>
      </div>
    </div>
  );
}

export default ProductDetail;