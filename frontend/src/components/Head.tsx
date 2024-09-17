import { Link, useNavigate } from "react-router-dom";
import "./head.css";
import { useAuth } from "../hooks/useAuth";
import { useCart } from "../hooks/useCart";

export function Head() {
  const navigate = useNavigate();
  const { cartItems } = useCart()
  const { isSigned, logout, user } = useAuth();

  function handleLogout() {
    logout();
    navigate("/");
  }

  return (
    <header>
      <div id="head">
        <Link to="/">
          <div id="logo">
            <h1 id="first-title">Estante</h1>
            <h1 id="second-title">Virtual</h1>
          </div>
        </Link>

        <div id="icons">
          <svg
            id="profile"
            xmlns="http://www.w3.org/2000/svg"
            width="1em"
            height="1em"
            viewBox="0 0 24 24"
          >
            <path
              fill="currentColor"
              d="M12 19.2c-2.5 0-4.71-1.28-6-3.2c.03-2 4-3.1 6-3.1s5.97 1.1 6 3.1a7.23 7.23 0 0 1-6 3.2M12 5a3 3 0 0 1 3 3a3 3 0 0 1-3 3a3 3 0 0 1-3-3a3 3 0 0 1 3-3m0-3A10 10 0 0 0 2 12a10 10 0 0 0 10 10a10 10 0 0 0 10-10c0-5.53-4.5-10-10-10"
            />
          </svg>
          <div className="menu-dropdown">
            <div className="menu-item">
              Olá, {isSigned ? user?.name : "Usuário"}{" "}
              <span className="arrow">&#9660;</span>
              <div className="dropdown-content">
                {!isSigned ? (
                  <Link to="/login" className="option">
                    Entrar
                  </Link>
                ) : null}
                {!isSigned ? (
                  <Link to="/register" className="option">
                    Cadastre-se
                  </Link>
                ) : null}
                {isSigned && user?.role === "CUSTOMER" ? (
                  <Link to="/profile" className="option">
                    Meu Perfil
                  </Link>
                ) : null}

                {isSigned && user?.role === "ADMIN" ? (
                  <Link to="/adm" className="option">
                    Área do adm.
                  </Link>
                ) : null}
                
                {isSigned ? (
                  <button onClick={handleLogout} className="option">
                    Sair
                  </button>
                ) : null}
              </div>
            </div>
          </div>

          <div>
            <button className="shopButton">
              {cartItems.length > 0 && (
                  <div className="badge">{cartItems.length}</div>
              )}
              <Link to="/CartShop">
                <svg
                  id="cart"
                  xmlns="http://www.w3.org/2000/svg"
                  width="1em"
                  height="1em"
                  viewBox="0 0 512 512"
                >
                  <circle cx="176" cy="416" r="32" fill="currentColor" />
                  <circle cx="400" cy="416" r="32" fill="currentColor" />
                  <path
                    fill="currentColor"
                    d="M456.8 120.78a23.92 23.92 0 0 0-18.56-8.78H133.89l-6.13-34.78A16 16 0 0 0 112 64H48a16 16 0 0 0 0 32h50.58l45.66 258.78A16 16 0 0 0 160 368h256a16 16 0 0 0 0-32H173.42l-5.64-32h241.66A24.07 24.07 0 0 0 433 284.71l28.8-144a24 24 0 0 0-5-19.93"
                  />
                </svg>
              </Link>
            </button>
          </div>
        </div>
      </div>
    </header>
  );
}
