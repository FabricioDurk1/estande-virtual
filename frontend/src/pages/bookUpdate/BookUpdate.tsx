import { useNavigate, useParams } from "react-router-dom";
import "./bookEdit.css";
import { useState, ChangeEvent, useEffect } from "react";
import { Publisher } from "../../models";
import { Author } from "../../models/Author";
import { api } from "../../services/api";
import {
  getOnlyNumbers,
  maskMoney,
  maskOnlyNumber,
  removeMoneyMask
} from "../../utils/masks";
import { Loader } from "../../components";

type Params = "bookId";

export function BookUpdate() {
  const navigate = useNavigate();
  const { bookId } = useParams<Params>();

  const [isLoading, setIsLoading] = useState(true);
  const [isSavingBook, setIsSavingBook] = useState(false);

  const [title, setTitle] = useState("");
  const [publisherId, setPublisherId] = useState("");
  const [authorId, setAuthorId] = useState("");
  const [stockQuantity, setStockQuantity] = useState("");
  const [price, setPrice] = useState("");
  const [description, setDescription] = useState("");

  const [publishers, setPublishers] = useState<Publisher[]>([]);
  const [authors, setAuthors] = useState<Author[]>([]);

  async function getPublishers() {
    const response = await api.get("/publishers");
    const loadedPublishers = response.data;

    setPublishers(loadedPublishers);
  }

  async function getAuthors() {
    const response = await api.get("/authors");
    const loadedAuthors = response.data;

    setAuthors(loadedAuthors);
  }

  async function getBook() {
    const response = await api.get(`/books/${bookId}`);
    const loadedBook = response.data;

    setTitle(loadedBook.title);
    setPublisherId(loadedBook.publisher.id);
    setAuthorId(loadedBook.author.id);
    setStockQuantity(loadedBook.quantity.toString());
    setPrice(maskMoney(loadedBook.price.toString()));
    setDescription(loadedBook.description);
  }

  async function loadInitialData() {
    try {
      setIsLoading(true);

      await getPublishers();
      await getAuthors();
      await getBook();

      setIsLoading(false);
    } catch (error) {
      setIsLoading(false);
      alert("Erro ao buscar dados iniciais");
    }
  }

  async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
    event.preventDefault();

    const requestBody = {
      title: title,
      publisherId: publisherId,
      authorId: authorId,
      quantity: getOnlyNumbers(stockQuantity),
      price: removeMoneyMask(price),
      description: description
    };

    try {
      setIsSavingBook(true);
      await api.put(`/books/${bookId}`, requestBody);
      alert("Livro atualizado com sucesso");
      navigate("/bookList");
    } catch (error) {
      setIsSavingBook(false);
      alert("Erro ao criar livro");
    }
  }

  useEffect(() => {
    loadInitialData();
  }, []);

  return (
    <div className="main-container">
      <div className="form-container">
        {isLoading ? (
          <Loader />
        ) : (
          <>
            <h1>Detalhes do Livro</h1>
            <div className="details-group">
              <form onSubmit={handleSubmit}>
                <br />
                <br />
                <div className="form-group">
                  <label htmlFor="titulo">Título</label>
                  <input
                    type="text"
                    id="titulo"
                    name="titulo"
                    onChange={(event) => setTitle(event.target.value)}
                    required
                    value={title}
                  ></input>
                </div>

                <div className="input-group">
                  <label className="publisher" htmlFor="editora">
                    Escolha a Editora:
                  </label>
                  <select
                    id="editora"
                    value={publisherId}
                    onChange={(event) => setPublisherId(event.target.value)}
                  >
                    <option value="">Selecione a Editora</option>
                    {publishers.map((publisher) => {
                      return (
                        <option key={publisher.id} value={publisher.id}>
                          {publisher.name}
                        </option>
                      );
                    })}
                  </select>
                </div>

                <div className="input-group">
                  <label className="author" htmlFor="autor">
                    Escolha o Autor:
                  </label>
                  <select
                    id="autor"
                    value={authorId}
                    onChange={(event) => setAuthorId(event.target.value)}
                  >
                    <option value="">Selecione o Autor</option>
                    {authors.map((author) => {
                      return (
                        <option key={author.id} value={author.id}>
                          {author.name}
                        </option>
                      );
                    })}
                  </select>
                </div>

                <div className="form-group">
                  <label htmlFor="preco">Preço</label>
                  <input
                    type="text"
                    id="preco"
                    name="preco"
                    required
                    value={price}
                    onChange={(event) =>
                      setPrice(maskMoney(event.target.value))
                    }
                  ></input>
                </div>

                <div className="form-group">
                  <label htmlFor="quantidade">Quantidade em Estoque</label>
                  <input
                    type="number"
                    id="quantidade"
                    name="quantidade"
                    onChange={(event) =>
                      setStockQuantity(maskOnlyNumber(event.target.value))
                    }
                    value={stockQuantity}
                    required
                  ></input>
                </div>

                <div className="form-group">
                  <label htmlFor="descricao">Descrição</label>
                  <textarea
                    id="descricao"
                    name="descricao"
                    required
                    onChange={(event) => setDescription(event.target.value)}
                    value={description}
                  />
                </div>

                <button
                  className="registerButton"
                  disabled={isSavingBook}
                  type="submit"
                >
                  Salvar
                </button>
              </form>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
