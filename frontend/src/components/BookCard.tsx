import "./bookCard.css";
import { formatAmountToCurrency } from "../utils/formatters";
import { Book } from "../models";
import { useCart } from "../hooks/useCart";

import bookTexture from "../assets/images/book-texture.jpg";
import { useNavigate } from "react-router-dom";

interface BookCardProps {
  book: Book;
}

function BookCard({ book }: BookCardProps) {
  const navigate = useNavigate();
  const { addCartItem, removeCartItem, selectedCartItemIds } = useCart();

  function handleClickBookCardCartButton(event: React.MouseEvent<HTMLElement>) {
    event.stopPropagation();

    if (selectedCartItemIds.includes(book.id)) {
      removeCartItem(book.id);
    } else {
      addCartItem(book, 1);
    }
  }

  function handleNavigateToBookDetails() {
    navigate(`/bookDescription/${book.id}`);
  }

  return (
    <div
      className="card-book"
      onClick={handleNavigateToBookDetails}
    >
      <div className="book-cover" style={{ backgroundImage: `url(${bookTexture})` }}>
        <p className="book-name">{book.title}</p>
      </div>

      <div className="label-price">
        <p>{formatAmountToCurrency(book.price)}</p>
        <div className="vertical-line"></div>

        <button
          onClick={handleClickBookCardCartButton}
          className={
            selectedCartItemIds.includes(book.id)
              ? "active cart-icon"
              : "cart-icon"
          }
        >
          <svg
            className="cart"
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 512 512"
          >
            <circle cx="176" cy="416" r="32" fill="currentColor" />
            <circle cx="400" cy="416" r="32" fill="currentColor" />
            <path
              fill="currentColor"
              d="M456.8 120.78a23.92 23.92 0 0 0-18.56-8.78H133.89l-6.13-34.78A16 16 0 0 0 112 64H48a16 16 0 0 0 0 32h50.58l45.66 258.78A16 16 0 0 0 160 368h256a16 16 0 0 0 0-32H173.42l-5.64-32h241.66A24.07 24.07 0 0 0 433 284.71l28.8-144a24 24 0 0 0-5-19.93"
            />
          </svg>
        </button>
      </div>
    </div>
  );
}

export default BookCard;
