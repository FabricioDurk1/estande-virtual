import { Link } from "react-router-dom";
import "./admnistrator.css";

export function Admnistrator() {
  return (
    <div className="total">
      <div className="form-container">
        <h1>Painel de Administração</h1>
        <Link to={"/editorList"}>
          <button className="registerButton">Lista de Editoras</button>
        </Link>

        <Link to={"/editorRegister"}>
          <button className="registerButton">Cadastro de Editora</button>
        </Link>

        <Link to={"/authorList"}>
          <button className="registerButton">Lista de autores</button>
        </Link>

        <Link to={"/authorRegister"}>
          <button className="registerButton">Cadastro de Autor</button>
        </Link>

        <Link to={"/bookList"}>
          <button className="registerButton">Lista de Livros</button>
        </Link>

        <Link to={"/bookRegister"}>
          <button className="registerButton">Cadastro de Livro</button>
        </Link>
      </div>
    </div>
  );
}
