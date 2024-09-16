import { useState, useEffect } from "react";

import { Book } from "../../models";
import { api } from "../../services/api";

import "./bookList.css";
import { Loader } from "../../components";
import { useNavigate } from "react-router-dom";

export function BookList() {
  const navigate = useNavigate();

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
    <div className="book-list-container">
      <div className="book-list-header">
        <h1 className="book-list-title">Lista de Livros</h1>
        <button onClick={() => navigate("/bookRegister")}>Cadastrar</button>
      </div>

      {isLoading ? (
        <Loader />
      ) : (
        <table>
          <thead>
            <tr>
              <th>Título</th>
              <th colSpan={2} className="actions">
                Ações
              </th>
            </tr>
          </thead>
          <tbody>
            {books.map((book) => (
              <tr key={book.id}>
                <td>{book.title}</td>
                <td className="btn-action-container">
                  <button className="btn btn-edit" onClick={() => navigate(`/bookUpdate/${book.id}`)}>Editar</button>
                </td>
                <td className="btn-action-container">
                  <button className="btn btn-delete">Deletar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
