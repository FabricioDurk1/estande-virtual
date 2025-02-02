import { Address } from "./Address";

export type User = {
  name: string;
  email: string;
  role: string;
  cpf: string;
  phone: string;
  birthDate: string;
  address: Address | null;
}