import { useEffect, useState } from "react";
import { CartItem, CartSubtotal } from "../../components";
import { BookQueueCard } from "../../components/BookQueueCard/BookQueueCard";
import { useCart } from "../../hooks/useCart";
import { formatAmountToCurrency } from "../../utils/formatters";
import "./cart.css";
import { Book } from "../../models";

function CartShop() {
  const { cartItems, total } = useCart();
  const [stackBooks, setStackBooks] = useState<Book[]>([]);
  const [allBooks, setAllBooks] = useState<Book[]>([]);

  function addOnStack(book: Book) {
    const updatedAllBooks = allBooks.filter((b) => b.id !== book.id);
    setStackBooks((prevState) => [book, ...prevState]);
    setAllBooks(updatedAllBooks);
  }

  function removeFromStack() {
    const removedBook = stackBooks[0];
    const updatedAllBooks = [...allBooks, removedBook];

    setAllBooks(updatedAllBooks);
    setStackBooks((prevState) => prevState.slice(1));
  }

  useEffect(() => {
    const books = cartItems.map((cartItem) => cartItem.book);
    setAllBooks(books);
    setStackBooks([]);
  }, [cartItems]);

  return (
    <>
      <div className="motherDiv">
        <div className="cartSelection">
          <div className="cartSelectionTitle">
            <h1 className="selectionCart">Carrinho de compras</h1>
          </div>

          {cartItems.length === 0 && (
            <div className="emptyCart">
              <p>Carrinho vazio</p>
            </div>
          )}

          {cartItems.map((cartItem) => {
            return <CartItem key={cartItem.book.id} cartItem={cartItem} />;
          })}
        </div>

        <div className="cartTotal">
          <div className="cartTotalTitle">
            <h1 className="totalCart">Total do carrinho </h1>

            {cartItems.map((cartItem) => (
              <CartSubtotal cartItem={cartItem} key={cartItem.book.id} />
            ))}

            <p className="cart-total">
              Valor total: <span>{formatAmountToCurrency(total)}</span>
            </p>

            {cartItems.length > 0 && (
              <div className="payment">
                <div className="payment-card">
                  <label id="creditCardLabel" htmlFor="creditCard">
                    Cartão de Crédito:
                  </label>

                  <select value={""} name="creditCard" required id="creditCard">
                    <option value={""}>Selecione seu cartão</option>
                  </select>

                  <button className="btnPayment">Finalizar compra</button>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>

      <div className="motherDiv">
        <div className="cartSelection">
          <div className="cartSelectionTitle">
            <h1 className="selectionCart">Organização dos livros no pacote</h1>
          </div>

          {cartItems.length === 0 && (
            <div className="emptyCart">
              <p>Carrinho vazio</p>
            </div>
          )}

          {allBooks.map((book) => {
            return (
              <BookQueueCard
                key={book.id}
                book={book}
                onPressAdd={() => addOnStack(book)}
              />
            );
          })}
        </div>

        <div className="cartTotal">
          <div className="cartTotalTitle">
            <h1 className="totalCart">Pacote</h1>

            {stackBooks.map((book) => (
              <p
                style={{ marginBottom: "1rem", textAlign: 'left', color: "#7A7A7A" }}
                key={book.id}
              >
                {book.title}
              </p>
            ))}

            <button className="remove-from-package" onClick={removeFromStack}>Remover do pacote</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default CartShop;
