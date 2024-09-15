import { CartItem as CartItemModel } from "../../models";
import { formatAmountToCurrency } from "../../utils/formatters";

import "./cartItem.css";
import { useCart } from "../../hooks/useCart";

interface Props {
  cartItem: CartItemModel;
}

export function CartItem({ cartItem }: Props) {
  const { decreaseQuantity, increaseQuantity, removeCartItem } = useCart()

  function handleIncreaseQuantity() {
    increaseQuantity(cartItem.book.id)
  }

  function handleDecreaseQuantity() {
    decreaseQuantity(cartItem.book.id)
  }

  function handleRemoveCartItem() {
    removeCartItem(cartItem.book.id)
  }

  return (
    <div className="cart-item">
      <div className="book-details">
        <h3 className="book-title">{cartItem.book.title}</h3>
        <p>
          <span>Valor Unitário:</span> {formatAmountToCurrency(cartItem.book.price)}
        </p>
        <p>
          <span>Subtotal:</span> {formatAmountToCurrency(cartItem.book.price * cartItem.quantity)}
        </p>
      </div>

      <div className="actions">
        <div className="quantity-buttons-container">
          <button onClick={handleDecreaseQuantity}>-</button>
          <span>{cartItem.quantity}</span>
          <button onClick={handleIncreaseQuantity}>+</button>
        </div>

        <button className="remove-btn" onClick={handleRemoveCartItem}>Remover do carrinho</button>
      </div>
    </div>
  );
}
