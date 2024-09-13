import './bookDescription.css';


export function BookDescription() {
    return (
        <div className="total2">
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
                <p>Nome da Editora Exemplo</p>wad
            </div>
            <br />
            <div className="period-group">
                <label htmlFor="period">Selecione o período:</label>
                <select id="period" name="period">
                    <option value="dias">Dias</option>
                    <option value="semanas">Semanas</option>
                    <option value="meses">Meses</option>
                </select>
            </div>

      
            <button className="registerButton" type="button">Ver Vendas</button>

           
            <div className="sales-list">
                <h2>Vendas no Período Selecionado</h2>
                <ul id="vendas-lista">
                    
                </ul>
                <h3 id="total-vendido">Total Vendido: 0 unidades</h3>
            </div>
        </div>
    </div>

    )
}


