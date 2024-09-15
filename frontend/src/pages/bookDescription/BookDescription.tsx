import './bookDescription.css';


export function BookDescription() {
    return (
        <div className="general">
        <div className="form-container">
            <h1>Detalhes do Livro</h1>

            <div className="details-group">
                <label className='general-labels'>Título:</label>
                <p>Nome do Livro Exemplo</p>
                <br />
                <label className='general-labels'>Descrição:</label>
                <p>Descrição detalhada do livro que explica seu conteúdo e público-alvo.</p>    
                <br />
                <label className='general-labels'>Autor:</label>
                <p>Nome do Autor Exemplo</p>
                <br />
                <label className='general-labels'>Editora:</label>
                <p>Nome da Editora Exemplo</p>
            </div>
            <br />
      
            <button className="registerButton" type="button">Ver Vendas</button>

        </div>
    </div>

    )
}


