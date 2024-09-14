import { ChangeEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import './editorRegister.css'

import { api } from '../../services/api';

export function EditorRegister() {
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);
    const [name, setName] = useState("");

    async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
        event.preventDefault();

        const requestBody = {
            name: name
        }   

        try {
            setIsLoading(true);
            await api.post("/publishers", requestBody);

            alert("Editora Cadastrada com sucesso");
            navigate("/editorList");
        } catch (error) {
            alert("Erro cadastrar editora");
        } finally{
            setIsLoading(false);
        }
    }

    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Cadastro de Editora</h1>
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="titulo">Nome da Editora</label>
                            <input type="text" id="titulo" name="titulo" required onChange={(event) => setName(event.target.value)}></input>
                        </div>

                        <button disabled={isLoading} className='registerButton' type="submit">Cadastrar Editora</button>
                    </form>
                </div>
        </div>
    )
}