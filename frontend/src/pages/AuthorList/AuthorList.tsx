import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import "./authorList.css";

import { Author } from "../../models/Author";
import { api } from "../../services/api";
import { Loader } from "../../components";

export function AuthorList() {
  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(false);
  const [authors, setAuthors] = useState<Author[]>([]);

  async function getAuthors() {
    try {
      setIsLoading(true);
      const response = await api.get<Author[]>("/authors");
      setAuthors(response.data);
    } catch (error) {
      alert("Erro ao buscar autores");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    getAuthors();
  }, []);

  return (
    <div className="author-list-container">
      <div className="author-list-header">
        <h1 className="author-list-title">Lista de Autores</h1>
        <button onClick={() => navigate("/authorRegister")}>Cadastrar</button>
      </div>

      {isLoading ? (
        <Loader />
      ) : (
        <table>
          <thead>
            <tr>
              <th>Nome</th>
              <th colSpan={2} className="actions">
                Ações
              </th>
            </tr>
          </thead>
          <tbody>
            {authors.map((author) => (
              <tr key={author.id}>
                <td>{author.name}</td>
                <td className="btn-action-container">
                  <button onClick={() => navigate(`/authorUpdate/${author.id}`)} className="btn btn-edit">Editar</button>
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
