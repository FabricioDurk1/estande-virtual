import { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import './login.css'

import { useAuth } from '../../hooks/useAuth';

function Login() {
    const navigate = useNavigate()
    const { login } = useAuth();

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [isLoading, setIsLoading] = useState(false);

    async function handleSubmit(event: ChangeEvent<HTMLFormElement>)
    {   
        event.preventDefault();

        try {
            setIsLoading(true);
            const result = await login(email, password);
            
            if(result.role === "ADMIN"){
                navigate("/adm")
            } else if(result.role === "CUSTOMER"){
                navigate("/")
            }
        } catch (error) {
            alert("Erro ao fazer login")
        } finally{
            setIsLoading(false);
        }
    }

    return (
        <div>
            <div className="login-container">
                <div className="login-form">
                    <h2>Faça login em sua conta</h2>
                    <form className='form' onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label id='email-label' htmlFor="email">Email:</label>
                            <input 
                                type="email" 
                                id="email" 
                                name="email" 
                                placeholder="Digite seu email"
                                onChange={(event) => setEmail(event.target.value)}
                            ></input>
                        </div>
                        <div className="form-group">
                            <label id='password-label' htmlFor="senha">Senha:</label>
                            <input 
                                type="password" 
                                id="senha" 
                                name="senha" 
                                placeholder="Digite sua senha"
                                onChange={(event) => setPassword(event.target.value)}
                            >
                            </input>
                        </div>
                        <button type="submit" className={isLoading ? 'btn-disable' : ''} disabled={isLoading ? true : false}>Entrar</button>
                    </form>
                </div>
            </div>

        </div>
    );
}

export default Login;