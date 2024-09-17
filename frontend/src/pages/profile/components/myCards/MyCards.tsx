import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";

import { CreditCard } from "../../../../models";
import { CreditCardCard } from "../creditCardCard/CreditCardCard";
import "./myCards.css";

import { api } from "../../../../services/api";
import { Loader } from "../../../../components";

type ApiCreditCard = {
  id: number;
  name: string;
  number: string;
  expirationDate: string;
  securityCode: string;
  flag: string;
  creditLimit: number;
}

export function MyCards() {
  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(false);
  const [creditCards, setCreditCards] = useState<CreditCard[]>([]);

  async function getAllCreditCards() {
    try {
      setIsLoading(true);
      const response = await api.get<ApiCreditCard[]>("/credit-cards");
      
      const loadedCreditCards: CreditCard[] = response.data.map((creditCard) => {
        return {
          id: creditCard.id,
          expirationDate: creditCard.expirationDate,
          flag: creditCard.flag,
          limit: creditCard.creditLimit,
          name: creditCard.name,
          number: creditCard.number,
          securityCode: creditCard.securityCode
        };
      });

      setCreditCards(loadedCreditCards);
    } catch (error) {
      alert("Erro ao buscar cartões de crédito");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    getAllCreditCards();
  }, []);

  function navigateToCreditCardRegister() {
    navigate("/creditCardRegister");
  }

  return (
    <div className="my-cards-page">
      <div className="my-cards-page-header">
        <h1>Meus cartões</h1>
        <button onClick={navigateToCreditCardRegister}>Cadastrar</button>
      </div>

      {isLoading ? (
        <Loader />
      ) : (
        <div className="credit-cards-container">
          {creditCards.map((creditCard) => {
            return (
              <CreditCardCard key={creditCard.id} creditCard={creditCard} />
            );
          })}
        </div>
      )}
    </div>
  );
}
