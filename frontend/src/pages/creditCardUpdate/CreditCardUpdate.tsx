import { ChangeEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import "./creditCardUpdate.css";

import { api } from "../../services/api";
import { getOnlyNumbers, maskCreditCardNumber, maskExpirationDate, maskMoney, maskOnlyNumber, removeExpirationDateMask, removeMoneyMask } from "../../utils/masks";
import { creditCardFlags } from "../../utils/constants/creditCard";
import { getFormattedExpirationDate } from "../../utils/formatters";

type ApiCreditCard = {
  id: number;
  name: string;
  number: string;
  expirationDate: string;
  securityCode: string;
  flag: string;
  creditLimit: number;
}


type Params = "creditCardId";

export function CreditCardUpdate() {
  const navigate = useNavigate();
  const params = useParams<Params>();

  const [isLoading, setIsLoading] = useState(false);
  const [flag, setFlag] = useState("");
  const [name, setName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [securityCode, setSecurityCode] = useState("");
  const [expirationDate, setExpirationDate] = useState("");
  const [limit, setLimit] = useState("")

  async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
    event.preventDefault();

    const requestBody = {
      name: name,
      number: getOnlyNumbers(cardNumber),
      expirationDate: removeExpirationDateMask(expirationDate),
      securityCode: getOnlyNumbers(securityCode),
      creditLimit: removeMoneyMask(limit),
      flag: flag
    };

    try {
      setIsLoading(true);
      await api.put(`/credit-cards/${params.creditCardId}`, requestBody);

      alert("Cartão de crédito atualizado com sucesso");
      navigate("/profile/myCards");
    } catch (error) {
      alert("Erro ao atualizar cartão de crédito");
    } finally {
      setIsLoading(false);
    }
  }

  async function getCreditCard() {
    try {
      setIsLoading(true);
      const response = await api.get<ApiCreditCard>(`/credit-cards/${params.creditCardId}`);

      const loadedCreditCard = response.data;
  
      setName(loadedCreditCard.name);
      setCardNumber(maskCreditCardNumber(loadedCreditCard.number));
      setSecurityCode(loadedCreditCard.securityCode);
      setExpirationDate(getFormattedExpirationDate(loadedCreditCard.expirationDate));
      setLimit(maskMoney(loadedCreditCard.creditLimit.toString()));
      setFlag(loadedCreditCard.flag);

    } catch (error) {
      alert("Erro ao buscar cartão de crédito");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    getCreditCard();
  }, []);

  return (
    <div className="total">
      <div className="form-container">
        <h1>Atualizar Cartão</h1>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="titulo">Nome do Cartão</label>
            <input
              type="text"
              id="titulo"
              name="titulo"
              placeholder="Informe o nome do seu cartão"
              required
              value={name}
              onChange={(event) => setName(event.target.value)}
            ></input>
          </div>

          <div className="form-group">
            <label htmlFor="titulo">Número do cartão</label>
            <input
              type="text"
              id="titulo"
              name="titulo"
              placeholder="XXXX XXXX XXXX XXXX"
              maxLength={19}
              required
              onChange={(event) => setCardNumber(maskCreditCardNumber(event.target.value))}
              value={cardNumber}
            ></input>
          </div>

          <div className="form-group">
            <label id="state-label" htmlFor="state">
              Bandeira
            </label>

            <select
              value={flag}
              name="state"
              required
              id="state"
              onChange={(event) => setFlag(event.target.value)}
            >
              <option value={""}>Selecione a bandeira</option>

              {creditCardFlags.map((option) => {
                return <option key={option.value} value={option.value}>{option.name}</option>;
              })}
            </select>
          </div>

          
          <div className="form-group">
            <label htmlFor="titulo">Código de Segurança</label>
            <input
              type="text"
              id="titulo"
              name="titulo"
              placeholder="CVV"
              maxLength={3}
              required
              value={securityCode}
              onChange={(event) => setSecurityCode(maskOnlyNumber(event.target.value))}
            ></input>
          </div>

          <div className="form-group">
            <label htmlFor="titulo">Data de Vencimento</label>
            <input
              type="text"
              id="titulo"
              name="titulo"
              placeholder="MM/AA"
              required
              maxLength={5}
              value={expirationDate}
              onChange={(event) => setExpirationDate(maskExpirationDate(event.target.value))}
            ></input>
          </div>

          <div className="form-group">
            <label htmlFor="titulo">Limite</label>
            <input
              type="text"
              id="titulo"
              placeholder="Informe o limite do seu cartão"
              name="titulo"
              value={limit}
              required
              onChange={(event) => setLimit(maskMoney(event.target.value))}
            ></input>
          </div>

          <button disabled={isLoading} className="registerButton" type="submit">
            Salvar
          </button>
        </form>
      </div>
    </div>
  );
}
