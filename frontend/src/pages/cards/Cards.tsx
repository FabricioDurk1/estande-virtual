import { useNavigate } from "react-router-dom";

import BookCard from "../../components/BookCard";
import { Book } from "../../models";

import "./cards.css";
import { useEffect, useState } from "react";
import { api } from "../../services/api";
import { Loader } from "../../components";
import { useAuth } from "../../hooks/useAuth";

function Cards() {
  const navigate = useNavigate();
  const { user } = useAuth();

  const [isLoading, setIsLoading] = useState(true);
  const [books, setBooks] = useState<Book[]>([]);

  async function getAllBooks() {
    setIsLoading(true);

    try {
      const response = await api.get("/public/books");
      setBooks(response.data);

      setIsLoading(false);
    } catch (error) {
      alert("Erro ao buscar livros");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    getAllBooks();
  }, []);

  return (
    <div className="cards">
      <div className="book-list-header">
        <h1 className="book-list-title">Lista de livros</h1>
        {user?.role === "ADMIN" && (
          <button
            className="book-list-button"
            onClick={() => navigate("/bookRegister")}
          >
            Cadastrar livro
          </button>
        )}
      </div>

      {isLoading ? (
        <Loader />
      ) : (
        <div className="general-books">
          {books.map((book) => {
            return <BookCard book={book} />;
          })}
        </div>
      )}
    </div>
  );
}

export default Cards;
