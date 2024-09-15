import './authorUpdate.css'

export function AuthorUpdate () {
    return(
        <div className="main-container-author">
        <div className="form-container">
            <h1>Detalhes do Autor</h1>
            <div className="details-group">
                <label>Nome:</label>
                <input className='all-inputs' type='text'></input>
            <button className="registerButton" type="button">Editar Autor</button>

        </div>
        </div>
        </div>
    )
}