import { useNavigate } from "react-router-dom";

import BookCard from "../../components/BookCard";
import { Book } from "../../models";

import "./cards.css";

const books: Book[] = [
  {
    id: 1,
    price: 10,
    description: "",
    title: "Livro 1"
  },
  {
    id: 2,
    price: 20,
    description: "",
    title: "Livro 2"
  },
  {
    id: 3,
    price: 30,
    description: "",
    title: "Livro 3"
  },
  {
    id: 4,
    price: 40,
    description: "",
    title: "Livro 4"
  }
];

function Cards() {
  const navigate = useNavigate();

  return (
    <div className="cards">
      <div className="book-list-header">
        <h1 className="book-list-title">Lista de livros</h1>
        <button className="book-list-button" onClick={() => navigate("/bookRegister")}>Cadastrar livro</button>
      </div>

      <div className="general-books">
        {books.map((book) => {
          return <BookCard book={book} />;
        })}
      </div>
    </div>
  );
}

export default Cards;
