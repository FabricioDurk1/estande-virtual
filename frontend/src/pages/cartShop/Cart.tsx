import { CartItem, CartSubtotal } from "../../components";
import { useCart } from "../../hooks/useCart";
import { formatAmountToCurrency } from "../../utils/formatters";
import "./cart.css";

function CartShop() {
  const { cartItems, total } = useCart();

  return (
    <>
      <div className="motherDiv">
        <div className="cartSelection">
          <div className="cartSelectionTitle">
            <h1 className="selectionCart">Carrinho de compras</h1>
          </div>

          {
            cartItems.length === 0 && (
              <div className="emptyCart">
                <p>Carrinho vazio</p>
              </div>
            )
          }

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
    </>
  );
}

export default CartShop;
