import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import "./editorList.css"

import { api } from "../../services/api";
import { Publisher } from "../../models";
import { Loader } from "../../components";

export function EditorList() {
    const navigate = useNavigate();

    const [isLoading, setIsLoading] = useState(false);
    const [publishers, setPublishers] = useState<Publisher[]>([]);
    
    async function getAllPublishers() {
        try {
            setIsLoading(true);
            const response = await api.get<Publisher[]>("/publishers");
            setPublishers(response.data);
        } catch (error) {
            alert("Erro ao buscar editoras");
        } finally {
            setIsLoading(false);
        }
    }

    useEffect(() => {
        getAllPublishers();
    }, []);

    return (
        <div className="publisher-list-container">
            <div className="publisher-list-header">
                <h1 className="publisher-list-title">Lista de Editoras</h1>
                <button onClick={() => navigate("/editorRegister")}>Cadastrar</button>
            </div>
        
            {
                isLoading ? (<Loader />) : (
                    <table>
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th colSpan={2} className="actions">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            {publishers.map((publisher) => (
                                <tr key={publisher.id}>
                                    <td>{publisher.name}</td>

                                    <td className="btn-action-container">
                                        <Link to={`/editorUpdate/${publisher.id}`}>
                                            <button className="btn btn-edit">Editar</button>
                                        </Link>
                                    </td>

                                    <td className="btn-action-container">
                                        <button className="btn btn-delete">Deletar</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )
            }
        </div>
    )
}