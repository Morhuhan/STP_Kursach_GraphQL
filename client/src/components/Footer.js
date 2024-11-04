import React from 'react';

function Footer() {
  return (
    <footer className="Footer">
      <p>&copy; {new Date().getFullYear()} Название вашей компании</p>
    </footer>
  );
}

export default Footer;