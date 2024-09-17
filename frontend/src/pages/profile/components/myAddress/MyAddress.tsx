import { useEffect, useState } from "react";

import "./myAddress.css";
import { useAuth } from "../../../../hooks/useAuth";
import { api } from "../../../../services/api";
import { getOnlyNumbers } from "../../../../utils/masks";

const brazilianStates = [
  { label: "Acre", value: "AC" },
  { label: "Alagoas", value: "AL" },
  { label: "Amapá", value: "AP" },
  { label: "Amazonas", value: "AM" },
  { label: "Bahia", value: "BA" },
  { label: "Ceará", value: "CE" },
  { label: "Distrito Federal", value: "DF" },
  { label: "Espírito Santo", value: "ES" },
  { label: "Goiás", value: "GO" },
  { label: "Maranhão", value: "MA" },
  { label: "Mato Grosso", value: "MT" },
  { label: "Mato Grosso do Sul", value: "MS" },
  { label: "Minas Gerais", value: "MG" },
  { label: "Pará", value: "PA" },
  { label: "Paraíba", value: "PB" },
  { label: "Paraná", value: "PR" },
  { label: "Pernambuco", value: "PE" },
  { label: "Piauí", value: "PI" },
  { label: "Rio de Janeiro", value: "RJ" },
  { label: "Rio Grande do Norte", value: "RN" },
  { label: "Rio Grande do Sul", value: "RS" },
  { label: "Rondônia", value: "RO" },
  { label: "Roraima", value: "RR" },
  { label: "Santa Catarina", value: "SC" },
  { label: "São Paulo", value: "SP" },
  { label: "Sergipe", value: "SE" },
  { label: "Tocantins", value: "TO" }
];

export function MyAddress() {
  const { user, updateUser } = useAuth();

  const [isLoading, setIsLoading] = useState(false);
  const [postalCode, setPostCode] = useState("");
  const [state, setState] = useState("");
  const [neighborhood, setNeighborhood] = useState("");
  const [street, setStreet] = useState("");
  const [addressNumber, setAddressNumber] = useState("");
  const [complement, setComplement] = useState("");
  const [city, setCity] = useState("");

  function maskPostalCode(value: string) {
    if (!value) return "";
    value = value.replace(/\D/g, "");
    value = value.replace(/(\d{5})(\d)/, "$1-$2");
    return value;
  }

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();

    try {
      setIsLoading(true);

      const requestBody = {
        street: street,
        number: addressNumber,
        complement: complement,
        neighborhood: neighborhood,
        city: city,
        state: state,
        zipCode: getOnlyNumbers(postalCode)
      };

      await api.put("/users/address", requestBody);

      if (user) {
        updateUser({
          ...user,

          address: {
            postalCode: requestBody.zipCode,
            state: requestBody.state,
            neighborhood: requestBody.neighborhood,
            street: requestBody.street,
            addressNumber: requestBody.number,
            complement: requestBody.complement,
            city: requestBody.city
          }
        });
      }

      alert("Dados atualizados com sucesso");
    } catch (error) {
      alert("Erro ao atualizar os dados");
    } finally {
      setIsLoading(false);
    }
  }

  useEffect(() => {
    if (user?.address) {
      const { address } = user;

      setPostCode(maskPostalCode(address.postalCode));
      setState(address.state);
      setNeighborhood(address.neighborhood);
      setStreet(address.street);
      setAddressNumber(address.addressNumber);
      setComplement(address.complement);
      setCity(address.city);
    }
  }, [user?.address]);

  return (
    <div className="register-container">
      <div className="register-form">
        <h2>Dados do endereço</h2>
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label id="postalCode-label" htmlFor="postalCode">
              CEP
            </label>
            <input
              type="postalCode"
              id="postalCode"
              name="postalCode"
              placeholder="Informe seu CEP"
              maxLength={9}
              value={postalCode}
              onChange={(event) =>
                setPostCode(maskPostalCode(event.target.value))
              }
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="state-label" htmlFor="state">
              Estado
            </label>
            <select
              value={state}
              name="state"
              required
              id="state"
              onChange={(event) => setState(event.target.value)}
            >
              <option value={""}>Selecione seu estado</option>

              {brazilianStates.map((option) => {
                return <option value={option.value}>{option.label}</option>;
              })}
            </select>
          </div>

          <div className="form-group">
            <label id="city-label" htmlFor="city">
              Cidade
            </label>
            <input
              type="city"
              id="city"
              name="city"
              placeholder="Informe sua cidade"
              value={city}
              onChange={(event) => setCity(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="neighborhood-label" htmlFor="neighborhood">
              Bairro
            </label>
            <input
              type="neighborhood"
              id="neighborhood"
              name="neighborhood"
              placeholder="Informe seu bairro"
              value={neighborhood}
              onChange={(event) => setNeighborhood(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="street-label" htmlFor="street">
              Rua:
            </label>
            <input
              id="street"
              name="street"
              placeholder="Informe sua rua"
              type="text"
              value={street}
              onChange={(event) => setStreet(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="address-number-label" htmlFor="addressNumber">
              Número:
            </label>
            <input
              type="text"
              id="addressNumber"
              name="addressNumber"
              placeholder="Informe seu número"
              value={addressNumber}
              onChange={(event) => setAddressNumber(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="complement-label" htmlFor="complement">
              Complemento:
            </label>
            <input
              type="text"
              id="complement"
              name="complement"
              placeholder="Digite seu complemento"
              value={complement}
              onChange={(event) => setComplement(event.target.value)}
            ></input>
          </div>

          <button type="submit" disabled={isLoading}>
            Salvar
          </button>
        </form>
      </div>
    </div>
  );
}
