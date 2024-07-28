import './login.css'

function Login() {
    return (
        <div>
            <div className="login-container">
                <div className="login-form">
                    <h2>Faça login em sua conta</h2>
                    <form className='form'>
                        <div className="form-group">
                            <label id='email-label' htmlFor="email">Email:</label>
                            <input type="email" id="email" name="email" placeholder="Digite seu email"></input>
                        </div>
                        <div className="form-group">
                            <label id='password-label' htmlFor="senha">Senha:</label>
                            <input type="password" id="senha" name="senha" placeholder="Digite sua senha">
                            </input>
                        </div>
                        <button type="submit">Entrar</button>
                    </form>
                </div>
            </div>

        </div>
    );
}

export default Login;