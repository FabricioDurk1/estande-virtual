import './register.css'

function Register() {
    return (
        <div>
            <div className="register-container">
                <div className="register-form">
                    <h2>Crie sua conta</h2>
                    <form className='form'>
                        <div className="form-group">
                            <label id='name-label' htmlFor="email">Nome Completo</label>
                            <input type="email" id="email" name="email" placeholder="Digite seu nome"></input>
                        </div>
                        <div className="form-group">
                            <label id='email-label' htmlFor="senha">Email:</label>
                            <input type="password" id="senha" name="senha" placeholder="Digite seu email"></input>
                        </div>
                        <div className="form-group">
                            <label id='password-label' htmlFor="email">Senha:</label>
                            <input type="email" id="email" name="email" placeholder="Digite sua senha"></input>
                        </div>
                        <div className="form-group">
                            <label id='cpf-label' htmlFor="senha">CPF</label>
                            <input type="password" id="senha" name="senha" placeholder="Digite seu CPF"></input>
                        </div>
                        <button type="submit">Criar conta</button>
                    </form>
                </div>
            </div>

        </div>
    );
}

export default Register;