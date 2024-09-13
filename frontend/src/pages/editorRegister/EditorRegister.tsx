import './editorRegister.css'

export function EditorRegister() {
    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Cadastro de Editora</h1>
                    <form>

                        <div className="form-group">
                            <label htmlFor="titulo">Nome da Editora</label>
                            <input type="text" id="titulo" name="titulo" required></input>
                        </div>

                        <button className='registerButton' type="submit">Cadastrar Editora</button>
                    </form>
                </div>
        </div>
    )
}