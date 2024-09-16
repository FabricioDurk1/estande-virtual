import "./authorUpdate.css";
import { ChangeEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { api } from "../../services/api";
import { Loader } from "../../components";
import { Author } from "../../models/Author";

type Params = "authorId";

export function AuthorUpdate() {
  const navigate = useNavigate();
  const params = useParams<Params>();

  const [isLoading, setIsLoading] = useState(true);
  const [name, setName] = useState("");

  async function getAuthor() {
    try {
      setIsLoading(true);
      const response = await api.get<Author>(`/authors/${params.authorId}`);
      const loadedPublisher = response.data;
      setName(loadedPublisher.name);
    } catch (error) {
      alert("Erro ao buscar editora");
    } finally {
      setIsLoading(false);
    }
  }

  async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
    event.preventDefault();

    const requestBody = {
      name: name
    };

    try {
      setIsLoading(true);
      await api.put(`/authors/${params.authorId}`, requestBody);
      alert("Editora editada com sucesso");
      setIsLoading(false);
      navigate("/authorsList");
    } catch (error) {
      setIsLoading(false);
      alert("Erro ao editar autor");
    }
  }

  useEffect(() => {
    getAuthor();
  }, []);

  return (
    <div className="main-container-author">
      <div className="form-container">
        <h1>Detalhes do Autor</h1>
        {isLoading ? (
          <div style={{ marginTop: "1rem", marginBottom: "1rem" }}>
            <Loader color="white" />
          </div>
        ) : (
          <form onSubmit={handleSubmit}>
            <div className="details-group">
              <label>Nome:</label>
              <input
                className="all-inputs"
                type="text"
                value={name}
                required
                onChange={(event) => setName(event.target.value)}
              ></input>
              <button
                disabled={isLoading}
                className="registerButton"
                type="button"
              >
                Editar Autor
              </button>
            </div>
          </form>
        )}
      </div>
    </div>
  );
}
