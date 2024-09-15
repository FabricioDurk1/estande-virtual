import './bookList.css'


export function BookList () {
    return (
        <div className="book-list-container">
        <div className="book-list-header">
            <h1 className="book-list-title">Lista de Livros</h1>
            <button>Cadastrar</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Título</th>
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