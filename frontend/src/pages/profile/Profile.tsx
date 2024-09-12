import { User } from '../../models/User';
import './profile.css'

interface Props {
  user: User | null;
}

export function Profile({ user }: Props){
  if(user === null){
    return <h1>Usuário não logado</h1>
  }

  return (
    <div>
      <div className="register-container">
          <div className="register-form">
              <h2>Dados da Conta</h2>
              <form className='form'>
                  <div className="form-group">
                      <label id='name-label' htmlFor="name">Nome Completo</label>
                      <input type="name" id="name" name="name" placeholder="Digite seu nome"value={user.name}></input>
                  </div>

                  <div className="form-group">
                      <label id='birth-date-label' htmlFor="birthDate">Date de nascimento:</label>
                      <input type="date" id="birthDate" name="birthDate" placeholder="Digite sua data de nascimento" value={user.birthDate}></input>
                  </div>

                  <div className="form-group">
                      <label id='email-label' htmlFor="email">Email:</label>
                      <input type="email" id="email" name="email" placeholder="Digite sue e-mail" value={user.email}></input>
                  </div>

                  <div className="form-group">
                      <label id='phone-label' htmlFor="phoneNumber">Telefone:</label>
                      <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="Digite seu telefone" value={user.phone}></input>
                  </div>

                  <div className="form-group">
                      <label id='cpf-label' htmlFor="cpf">CPF</label>
                      <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" maxLength={11}value={user.cpf}></input>
                  </div>

              </form>
          </div>
      </div>
    </div>
  )
}