import { useEffect, useState } from "react";

import "./myAccount.css";
import { useAuth } from "../../../../hooks/useAuth";
import { api } from "../../../../services/api";


export function MyAccount() {
  const { user } = useAuth()

  const [isLoading, setIsLoading] = useState(false);
  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [email, setEmail] = useState("");
  const [cpf, setCpf] = useState("");
  const [phone, setPhone] = useState("");

  function maskCPF(value: string) {
    value = value.replace(/\D/g, "");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");

    value = value.replace(/(\d{3})(\d{1,2})$/, "$1-$2");
    return value;
  }

  function maskPhone(value: string) {
    if (!value) return ""

    value = value.replace(/\D/g,'')
    value = value.replace(/(\d{2})(\d)/,"($1) $2")
    value = value.replace(/(\d)(\d{4})$/,"$1-$2")

    return value
  }


  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();

    try {
      setIsLoading(true);

      const requestBody = {
        name: name,
        birthDate: birthDate,
        email: email,
        cpf: cpf.replace(/\D/g, ""),
        phone: phone.replace(/\D/g, "")
      }

      await api.put("/users", requestBody);

      alert("Dados atualizados com sucesso");
    } catch (error) {
      alert("Erro ao atualizar os dados");
    } finally {
      setIsLoading(false);
    }
  }


  useEffect(() => {
    if (user) {
      setName(user.name)
      setBirthDate(user.birthDate)
      setEmail(user.email)
      setCpf(maskCPF(user.cpf))
      setPhone(maskPhone(user.phone))
    }

  }, [user])

  return (
    <div className="register-container">
      <div className="register-form">
        <h2>Dados da Conta</h2>
        <form className="form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label id="name-label" htmlFor="name">
              Nome Completo
            </label>
            <input
              type="name"
              id="name"
              name="name"
              placeholder="Digite seu nome"
              value={name}
              onChange={(event) => setName(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="birth-date-label" htmlFor="birthDate">
              Date de nascimento:
            </label>
            <input
              type="date"
              id="birthDate"
              name="birthDate"
              placeholder="Digite sua data de nascimento"
              value={birthDate}
              onChange={(event) => setBirthDate(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="email-label" htmlFor="email">
              Email:
            </label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Digite sue e-mail"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="phone-label" htmlFor="phoneNumber">
              Telefone:
            </label>
            <input
              id="phoneNumber"
              name="phoneNumber"
              placeholder="Digite seu telefone"
              type="tel" 
              maxLength={15}
              value={phone}
              onChange={(event) => setPhone(maskPhone(event.target.value))}
              required
            ></input>
          </div>

          <div className="form-group">
            <label id="cpf-label" htmlFor="cpf">
              CPF
            </label>
            <input
              type="text"
              id="cpf"
              name="cpf"
              placeholder="Digite seu CPF"
              value={cpf}
              maxLength={14}
              onChange={(event) => setCpf(maskCPF(event.target.value))}
              required
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
