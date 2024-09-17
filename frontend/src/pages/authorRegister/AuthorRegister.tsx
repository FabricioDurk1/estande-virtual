import { useState, ChangeEvent } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../../services/api';
import './authorRegister.css'

export function AuthorRegister() {
    const navigate = useNavigate();
  
    const [isLoading, setIsLoading] = useState(false);
    const [name, setName] = useState("");
  
    async function handleSubmit(event: ChangeEvent<HTMLFormElement>) {
      event.preventDefault();
  
      const requestBody = {
        name: name
      };
  
      try {
        setIsLoading(true);
        await api.post(`/authors`, requestBody);
        alert("Autor criado com sucesso");
        setIsLoading(false);
        navigate("/authorList");
      } catch (error) {
        setIsLoading(false);
        alert("Erro ao cadastrar autor");
      }
    }
  

    return (
        <div className='total'>
                <div className="form-container">
                    <h1>Cadastro de Autor</h1>
                    <form onSubmit={handleSubmit}>
                        <div className="form-group">
                            <label htmlFor="titulo">Nome do Autor</label>
                            <input type="text" id="titulo" name="titulo" required value={name} onChange={(event) => setName(event.target.value)}></input>
                        </div>

                        <button className='registerButton' disabled={isLoading} type="submit">Cadastrar Autor</button>
                    </form>
                </div>
        </div>
    )
}