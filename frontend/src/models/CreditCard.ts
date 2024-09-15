export type CreditCard = {
  id: string;
  number: string;
  flag: string;
  name: string;
  expirationDate: Date;
  securityCode: string;
  limit: number;
}