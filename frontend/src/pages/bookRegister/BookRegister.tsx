import './bookRegister.css'

export function BookRegister() {
    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Cadastro de Livro</h1>
                    <form>
                        <div className="form-group">
                            <label htmlFor="autor">Autor</label>
                            <input type="text" id="autor" name="autor" required></input>
                        </div>

                        <div className="form-group">
                            <label htmlFor="titulo">Título</label>
                            <input type="text" id="titulo" name="titulo" required></input>
                        </div>

                        <div className="form-group">
                            <label htmlFor="editora">Editora</label>
                            <input type="text" id="editora" name="editora" required></input>
                        </div>

                        <div className="form-group">
                            <label htmlFor="preco">Preço</label>
                            <input type="number" id="preco" name="preco" step="0.01" required></input>
                        </div>

                        <div className="form-group">
                            <label htmlFor="quantidade">Quantidade em Estoque</label>
                            <input type="number" id="quantidade" name="quantidade" required></input>
                        </div>

                        <div className="form-group">
                            <label htmlFor="descricao">Descrição</label>
                            <textarea id="descricao" name="descricao" required></textarea>
                        </div>

                        <button className='registerButton' type="submit">Cadastrar Livro</button>
                    </form>
                </div>
        </div>
    )
}