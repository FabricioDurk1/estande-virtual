import { ChangeEvent, useState } from 'react';

import './register.css'
import { api } from '../../services/api';

type RegisterFormData = {
    name: string;
    email: string;
    password: string;
    cpf: string;
    phoneNumber: string;
    birthDate: string;
}

function Register() {
    const [registerFormData, setRegisterFormData] = useState<RegisterFormData>({ 
        name: '',
        email: '',
        cpf: '',
        password: '',
        phoneNumber: '',
        birthDate: ''
    });

    function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
        event.preventDefault();

        const requestBody = {
            cpf: registerFormData.cpf,
            email: registerFormData.email,
            name: registerFormData.name,
            password: registerFormData.password,
            phoneNumber: registerFormData.phoneNumber,
            birthDate: registerFormData.birthDate
        }

        api.post("/auth/register", requestBody)
    }

    return (
        <div>
            <div className="register-container">
                <div className="register-form">
                    <h2>Crie sua conta</h2>
                    <form className='form' onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label id='name-label' htmlFor="name">Nome Completo</label>
                            <input type="name" id="name" name="name" placeholder="Digite seu nome" onChange={(event) => setRegisterFormData({ ...registerFormData, name: event.target.value })} required></input>
                        </div>

                        <div className="form-group">
                            <label id='birth-date-label' htmlFor="birthDate">Date de nascimento:</label>
                            <input type="date" id="birthDate" name="birthDate" placeholder="Digite sua data de nascimento"  onChange={(event) => setRegisterFormData({ ...registerFormData, birthDate: event.target.value })} required></input>
                        </div>

                        <div className="form-group">
                            <label id='email-label' htmlFor="email">Email:</label>
                            <input type="email" id="email" name="email" placeholder="Digite sue e-mail"  onChange={(event) => setRegisterFormData({ ...registerFormData, email: event.target.value })} required></input>
                        </div>

                        <div className="form-group">
                            <label id='password-label' htmlFor="senha">Senha:</label>
                            <input type="password" id="senha" name="senha" placeholder="Digite sua senha"  onChange={(event) => setRegisterFormData({ ...registerFormData, password: event.target.value })} required></input>
                        </div>

                        <div className="form-group">
                            <label id='phone-label' htmlFor="phoneNumber">Telefone:</label>
                            <input type="tel" id="phoneNumber" name="phoneNumber" placeholder="Digite seu telefone"  onChange={(event) => setRegisterFormData({ ...registerFormData, phoneNumber: event.target.value })} required></input>
                        </div>

                        <div className="form-group">
                            <label id='cpf-label' htmlFor="cpf">CPF</label>
                            <input type="text" id="cpf" name="cpf" placeholder="Digite seu CPF" maxLength={11} onChange={(event) => setRegisterFormData({ ...registerFormData, cpf: event.target.value })} required></input>
                        </div>
                        <button type="submit">Criar conta</button>
                    </form>
                </div>
            </div>

        </div>
    );
}

export default Register;