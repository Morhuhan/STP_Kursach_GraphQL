import React, { useState, useEffect } from 'react';
import { ApolloProvider } from '@apollo/client';
import { createApolloClient } from './access/apolloClient';
import axios from 'axios';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginForm from './access/LoginForm';
import HomePage from './pages/HomePage';
import ProductDetail from './pages/ProductDetail';
import Cart from './components/Cart';
import Header from './components/Header';
import Footer from './components/Footer';

function App() {
  const [authenticated, setAuthenticated] = useState(false);
  const client = createApolloClient();

  useEffect(() => {
    const checkAuth = async () => {
      try {
        axios.defaults.withCredentials = true;
        const response = await axios.get('http://localhost:8080/auth/status');
        if (response.status === 200) {
          setAuthenticated(true);
        }
      } catch (error) {
        setAuthenticated(false);
      }
    };

    checkAuth();
  }, []);

  const handleLogin = () => {
    setAuthenticated(true);
  };

  const handleLogout = async () => {
    try {
      await axios.post('http://localhost:8080/logout');
      setAuthenticated(false);
    } catch (error) {
      console.error('Logout failed:', error);
    }
  };

  return (
    <ApolloProvider client={client}>
      <Router>
        {authenticated && <Header onLogout={handleLogout} />}
        <Routes>
          {!authenticated ? (
            <>
              <Route path="/login" element={<LoginForm onLogin={handleLogin} />} />
              <Route path="*" element={<Navigate to="/login" replace />} />
            </>
          ) : (
            <>
              <Route path="/product/:id" element={<ProductDetail />} />
              <Route path="/cart" element={<Cart />} /> 
              <Route path="/" element={<HomePage />} />
              <Route path="*" element={<Navigate to="/" replace />} />
            </>
          )}
        </Routes>
        {authenticated && <Footer />}
      </Router>
    </ApolloProvider>
  );
}

export default App;