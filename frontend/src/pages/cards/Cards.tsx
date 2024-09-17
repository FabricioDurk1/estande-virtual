import { useNavigate } from "react-router-dom";

import BookCard from "../../components/BookCard";
import { Book } from "../../models";

import "./cards.css";
import { useEffect, useMemo, useState } from "react";
import { api } from "../../services/api";
import { Loader } from "../../components";
import { useAuth } from "../../hooks/useAuth";

function Cards() {
  const navigate = useNavigate();
  const { user } = useAuth();

  const [searchText, setSearchText] = useState("");
  const [isLoading, setIsLoading] = useState(true);
  const [books, setBooks] = useState<Book[]>([]);

  const { filteredBooks } = useMemo(() => {
    return {
      filteredBooks: books.filter((book) => book.title.toLowerCase().includes(searchText.toLowerCase())),
    };
  }, [books, searchText]);

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

        <input
          type="text"
          id="searchBookInput"
          name="searchBook"
          placeholder="Pesquisar livros..."
          onChange={(event) => setSearchText(event.target.value)}
        ></input>

        {user?.role && "ADMIN" && (
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
          {filteredBooks.map((book) => {
            return <BookCard book={book} />;
          })}
        </div>
      )}
    </div>
  );
}

export default Cards;
