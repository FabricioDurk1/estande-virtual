import { ChangeEvent, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import "./creditCardUpdate.css";

import { api } from "../../services/api";
import { getOnlyNumbers, maskCreditCardNumber, maskExpirationDate, maskMoney, maskOnlyNumber, removeMoneyMask } from "../../utils/masks";
import { creditCardFlags } from "../../utils/constants/creditCard";

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
      cardName: name,
      cardNumber: getOnlyNumbers(cardNumber),
      expirationDate: expirationDate,
      securityCode: getOnlyNumbers(securityCode),
      limit: removeMoneyMask(limit),
      flag: flag
    };

    try {
      setIsLoading(true);
      await api.put(`/credit-card/${params.creditCardId}`, requestBody);

      alert("Cartão de crédito atualizado com sucesso");
      navigate("/profile");
    } catch (error) {
      alert("Erro ao atualizar cartão de crédito");
    } finally {
      setIsLoading(false);
    }
  }

  async function getCreditCard() {
    try {
      setIsLoading(true);
      const response = await api.get(`/credit-card/${params.creditCardId}`);

      const loadedCreditCard = response.data;
      
      setName(loadedCreditCard.cardName);
      setCardNumber(maskCreditCardNumber(loadedCreditCard.cardNumber));
      setSecurityCode(loadedCreditCard.securityCode);
      setExpirationDate(loadedCreditCard.expirationDate);
      setLimit(maskMoney(loadedCreditCard.limit));
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
        <h1>Cadastrar Cartão</h1>
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
              <option value={""}>Selecione seu estado</option>

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
            Cadastrar
          </button>
        </form>
      </div>
    </div>
  );
}
