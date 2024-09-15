import { createContext, useMemo, useState } from "react";
import { Book, CartItem } from "../models";

interface CartContextData {
  cartItems: CartItem[];
  addCartItem: (book: Book, quantity: number) => void;
  removeCartItem: (id: number) => void;
  increaseQuantity: (id: number) => void;
  decreaseQuantity: (id: number) => void;
  clearCart: () => void;
  total: number;
  selectedCartItemIds: number[];
}

export const CartContext = createContext<CartContextData>({} as CartContextData)

interface CartContextProviderProps {
  children: React.ReactNode
}

export function CartContextProvider({ children }: CartContextProviderProps) {
  const [cartItems, setCartItems] = useState<CartItem[]>([]);

  const total = useMemo(() => {
    const total = cartItems.reduce((acc, item) => {
      return acc + item.book.price * item.quantity
    }, 0)

    return total
  }, [cartItems])

  const selectedCartItemIds = useMemo(() => {
    const selectedCartItemIds = cartItems.map(item => item.book.id)
    return selectedCartItemIds
  }, [cartItems])

  function addCartItem(book: Book, quantity: number) {
    const newCartItem: CartItem = {
      book,
      quantity
    }

    setCartItems([...cartItems, newCartItem])
  }

  function removeCartItem(id: number) {
    const remainingCartItems = cartItems.filter(item => item.book.id !== id)
    setCartItems(remainingCartItems)
  }

  function increaseQuantity(id: number) {
    const updatedCartItems = cartItems.map(item => {
      if (item.book.id === id) {
        return { ...item, quantity: item.quantity + 1 }
      } else {
        return item
      }
    })
  
    setCartItems(updatedCartItems)
  }

  function decreaseQuantity(id: number) {
    const updatedCartItems = cartItems.map(item => {
      if (item.book.id === id && item.quantity > 1) {
        return { ...item, quantity: item.quantity - 1 }
      } else {
        return item
      }
    })
  
    setCartItems(updatedCartItems)
  }

  function clearCart() {
    setCartItems([])
  }

  return (
    <CartContext.Provider
      value={{
        cartItems,
        addCartItem,
        removeCartItem,
        increaseQuantity,
        decreaseQuantity,
        clearCart,
        total,
        selectedCartItemIds
      }}
    >
      {children}
    </CartContext.Provider>
  )
}  