import './authorList.css'

export function AuthorList() {
    return(
    <div className="author-list-container">
        <div className="author-list-header">
            <h1 className="author-list-title">Lista de Autores</h1>
            <button>Cadastrar</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th colSpan={2} className="actions">Ações</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td></td>
                    <td className="btn-action-container">
                        <button className="btn btn-edit">Editar</button>
                    </td>

                    <td className="btn-action-container">
                        <button className="btn btn-delete">Deletar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    )
}