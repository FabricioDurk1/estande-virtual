import { useNavigate } from "react-router-dom";
import { CreditCard } from "../../../../models";
import { formatAmountToCurrency, formatCreditCardNumber, getFormattedExpirationDate } from "../../../../utils/formatters";

import "./creditCardCard.css";

type Props = {
  creditCard: CreditCard;
}

export function CreditCardCard({ creditCard }: Props){
  const navigate = useNavigate()

  function navigateToEditCreditCard() {
    navigate(`/creditCardUpdate/${creditCard.id}`)
  }

  return (
    <div className="credit-card-container">
      <div className="credit-card-header">
        <h2>{creditCard.name} - {creditCard.flag}</h2>
        <button onClick={navigateToEditCreditCard}>Editar</button>
      </div>
      <div className="credit-card-info-row">
        <p><span>Número: </span>{formatCreditCardNumber(creditCard.number)}</p>
        <p><span>Vencimento: </span>{getFormattedExpirationDate(creditCard.expirationDate)}</p>
      </div>

      <p><span>Limite: </span>{formatAmountToCurrency(creditCard.limit)}</p>
    </div>
  )
}