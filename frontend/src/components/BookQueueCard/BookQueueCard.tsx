import { Book} from "../../models";

import "./bookQueueCard.css";

interface Props {
  book: Book;
  onPressAdd: (book: Book) => void;
}

export function BookQueueCard({ book, onPressAdd }: Props) {
  return (
    <div className="cart-queue-item">
      <div className="book-details">
        <h3 className="book-title">{book.title}</h3>
      </div>

      <div className="actions">
        <button className="remove-btn" onClick={() => onPressAdd(book)}>Adicionar no pacote</button>
      </div>
    </div>
  );
}
