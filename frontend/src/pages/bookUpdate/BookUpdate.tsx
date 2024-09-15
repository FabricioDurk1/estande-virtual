import './bookEdit.css'


export function BookUpdate() {
    return(
        <div className="main-container">
        <div className="form-container">
            <h1>Detalhes do Livro</h1>

            <div className="details-group">
                <label>Título:</label>
                <input className='all-inputs' type='text'></input>
                <br />
                <label>Descrição:</label>
                <textarea id="descricao" name="descricao" rows={4} cols={50}></textarea> 
                <br />
                <label>Autor:</label>
                <input className='all-inputs' type='text'></input>
                <br />
                <label>Editora:</label>
                <input className='all-inputs' type='text'></input>
            </div>
            <br />
    
            <button className="registerButton" type="button">Editar Livro</button>

        </div>
        </div>
    )
}