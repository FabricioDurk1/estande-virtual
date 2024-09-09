import './bookDescription.css';
import brasCubas from '../../assets/images/brasCubas.webp'
export function BookDescription() {
    return (
        <div>
                <div className="container">
                    <div className="book-details">
                        <div className="book-image">
                            <img src={brasCubas} alt="Capa do Livro"></img>
                        </div>
                        <div className="book-info">
                            <h1 className="book-title">Título do Livro</h1>
                            <h2 className="book-author">Autor do Livro</h2>
                            <p className="book-description">
                                Esta é uma descrição fictícia do livro. O livro trata de um tema interessante e cativante, abordando diversos aspectos importantes de forma clara e envolvente. É uma leitura recomendada para todos que se interessam por esse gênero.
                            </p>
                            <p className="book-price">R$ 59,90</p>
                            <div className="rating">
                                Avaliação:
                                <span>&#9733;&#9733;&#9733;&#9733;&#9734;</span>
                                (4.0 de 5.0)
                            </div>
                            <a href="#" className="add-to-cart">Adicionar ao Carrinho</a>
                        </div>
                    </div>
                </div>
        </div>
    )
}


