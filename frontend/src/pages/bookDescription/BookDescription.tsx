import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { api } from "../../services/api";
import { Book } from "../../models";

import "./bookDescription.css";
import { Loader } from "../../components";

type Params = "bookId";

export function BookDescription() {
  const { bookId } = useParams<Params>();

  const [isLoading, setIsLoading] = useState(true);
  const [book, setBook] = useState<Book>();

  async function getAuthor() {
    try {
      setIsLoading(true);
      const response = await api.get<Book>(`/public/books/${bookId}`);
      const loadedBook = response.data;

      setBook(loadedBook);
    } catch (error) {
      alert("Erro ao buscar livro");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    getAuthor();
  }, []);

  return (
    <div className="general">
      {isLoading ? (
        <Loader />
      ) : (
        <div className="form-container">
          <h1>Detalhes do Livro</h1>

          <div className="details-group">
            <label className="general-labels">Título:</label>
            <p>{book?.title}</p>
            <br />
            <label className="general-labels">Descrição:</label>
            <p>
              {book?.description}
            </p>
            <br />
            <label className="general-labels">Autor:</label>
            <p>{book?.author.name}</p>
            <br />
            <label className="general-labels">Editora:</label>
            <p>{book?.publisher.name}</p>
          </div>
        </div>
      )}
    </div>
  );
}
