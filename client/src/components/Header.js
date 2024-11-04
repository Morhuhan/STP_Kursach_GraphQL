import React from 'react';
import { Link } from 'react-router-dom';

function Header({ onLogout }) {
  return (
    <header className="Header">
      <nav>
        <ul>
          <li>
            <Link to="/">Главная</Link>
          </li>
          <li>
            <Link to="/cart">Корзина</Link>
          </li>
        </ul>
      </nav>
      <button onClick={onLogout}>Выйти</button>
    </header>
  );
}

export default Header;