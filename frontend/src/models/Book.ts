import { Author } from "./Author";
import { Publisher } from "./Publisher";

export type Book = {
  id: number;
  title: string;
  description: string;
  price: number;
  author: Author;
  publisher: Publisher;
}