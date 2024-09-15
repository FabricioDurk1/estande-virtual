import { useNavigate } from "react-router-dom";
import { CreditCard } from "../../../../models";
import { CreditCardCard } from "../creditCardCard/CreditCardCard";
import "./myCards.css";

const creditCards: CreditCard[] = [
  {
    id: '1',
    number: '4111111111111111',
    flag: 'Visa',
    name: 'João Silva',
    expirationDate: new Date('2026-12-31'),
    securityCode: '123',
    limit: 5000
  },
  {
    id: '2',
    number: '5500000000000004',
    flag: 'MasterCard',
    name: 'Maria Oliveira',
    expirationDate: new Date('2025-09-30'),
    securityCode: '456',
    limit: 3000
  },
  {
    id: '3',
    number: '340000000000009',
    flag: 'American Express',
    name: 'Carlos Souza',
    expirationDate: new Date('2027-01-31'),
    securityCode: '789',
    limit: 7000
  },
  {
    id: '4',
    number: '6011000000000004',
    flag: 'Discover',
    name: 'Ana Santos',
    expirationDate: new Date('2024-11-30'),
    securityCode: '321',
    limit: 2500
  },
  {
    id: '5',
    number: '3530111333300000',
    flag: 'JCB',
    name: 'Pedro Lima',
    expirationDate: new Date('2026-05-31'),
    securityCode: '654',
    limit: 4500
  }
];

export function MyCards() {
  const navigate = useNavigate()

  function navigateToCreditCardRegister() {
    navigate('/creditCardRegister')
  }

  return (
    <div className="my-cards-page">

      <div className="my-cards-page-header">
        <h1>Meus cartões</h1>
        <button onClick={navigateToCreditCardRegister}>Cadastrar</button>
      </div>

      <div className="credit-cards-container">
        {
          creditCards.map((creditCard) => {
            return (
              <CreditCardCard key={creditCard.id} creditCard={creditCard}/>
            )
          })
        }
      </div>
    </div>
  );
}