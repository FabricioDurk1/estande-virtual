import './bookEdit.css'


export function BookEdit() {
    return(
        <div className="main-container">
        <div className="form-container">
            <h1>Detalhes do Livro</h1>

            <div className="details-group">
                <label>Título:</label>
                <p>Nome do Livro Exemplo</p>
                <br />
                <label>Descrição:</label>
                <p>Descrição detalhada do livro que explica seu conteúdo e público-alvo.</p>    
                <br />
                <label>Autor:</label>
                <p>Nome do Autor Exemplo</p>
                <br />
                <label>Editora:</label>
                <input>Nome da edita exemplo:</input>
            </div>
            <br />
    
            <button className="registerButton" type="button">Editar Livro</button>

        </div>
        </div>
    )
}