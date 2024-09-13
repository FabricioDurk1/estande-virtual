import './authorRegister.css'

export function AuthorRegister() {
    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Cadastro de Autor</h1>
                    <form>

                        <div className="form-group">
                            <label htmlFor="titulo">Nome do Autor</label>
                            <input type="text" id="titulo" name="titulo" required></input>
                        </div>

                        <button className='registerButton' type="submit">Cadastrar Autor</button>
                    </form>
                </div>
        </div>
    )
}