import { CartItem } from "../../models";
import { formatAmountToCurrency } from "../../utils/formatters";
import "./cartSubtotal.css";

interface Props {
  cartItem: CartItem;
}

export function CartSubtotal({ cartItem }: Props) {
  return (
    <div className="cart-total-item" key={cartItem.book.id}>
      <p>
        {formatAmountToCurrency(cartItem.book.price)} x {cartItem.quantity}
      </p>
      <p className="cart-total-item-amount">
        {formatAmountToCurrency(cartItem.book.price * cartItem.quantity)}
      </p>
    </div>
  );
}
