import { ChangeEvent, useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import './editorUpdate.css'

import { api } from '../../services/api';
import { Publisher } from '../../models';
import { Loader } from '../../components';

type Params = "publisherId";

export function EditorUpdate() {
    const navigate = useNavigate();
    const params = useParams<Params>();

    const [isLoading, setIsLoading] = useState(true);
    const [name, setName] = useState('');

    async function getPublisher() {
        try {
            setIsLoading(true);
            const response = await api.get<Publisher>(`/publishers/${params.publisherId}`);
            const loadedPublisher = response.data;
            setName(loadedPublisher.name);
        } catch (error) {
            alert("Erro ao buscar editora");
            navigate("/editorList");
        } finally {
            setIsLoading(false);
        }
    }

    async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
        event.preventDefault();

        const requestBody = {
            name: name
        }

        try {
            setIsLoading(true);
            await api.put(`/publishers/${params.publisherId}`, requestBody);
            alert("Editora editada com sucesso");
            setIsLoading(false);
            navigate("/editorList");
        } catch (error) {
            setIsLoading(false);
            alert("Erro ao editar editora");
        }
    }

    useEffect(() => {
        getPublisher();
    }, []);

    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Editar Editora</h1>

                    {
                        isLoading ? (
                            <div style={{ marginTop: "1rem", marginBottom: "1rem" }}>
                                <Loader color='white' />
                            </div>
                        ) : (
                            <form onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label htmlFor="titulo">Nome da Editora</label>
                                    <input type="text" id="titulo" name="titulo" value={name} required onChange={(event) => setName(event.target.value)}></input>
                                </div>
    
                                <button disabled={isLoading} className='registerButton' type="submit">Salvar</button>
                            </form>
                        )
                    }
                </div>
        </div>
    )
}