import './bookRegister.css'

export function BookRegister() {
    return (
        <div className='principalContainer'>
            <div className="form-container">
                <h1>Cadastro de Livro</h1>
                <form>
                    <br />
                    <br />
                    <div className="form-group">
                        <label htmlFor="titulo">Título</label>
                        <input type="text" id="titulo" name="titulo" required></input>
                    </div>

                    <div className="input-group">
                        <label className='publisher' htmlFor="editora">Escolha a Editora:</label>
                        <select id="editora">
                            <option value="">Selecione a Editora</option>
                            <option value="editora1">Editora 1</option>
                            <option value="editora2">Editora 2</option>
                            <option value="editora3">Editora 3</option>
                        </select>
                    </div>

                    <div className="input-group">
                        <label className='author' htmlFor="autor">Escolha o Autor:</label>
                        <select id="autor">
                            <option value="">Selecione o Autor</option>
                            <option value="autor1">Autor 1</option>
                            <option value="autor2">Autor 2</option>
                            <option value="autor3">Autor 3</option>
                        </select>
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