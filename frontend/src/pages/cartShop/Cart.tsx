import './cart.css';
import imageBookCart from '../../assets/images/bikeguy.svg'

function CartShop() {
    return (
        <>
            <div className='motherDiv'>
                <div className='cartSelection'>
                    <div className='cartSelectionTitle'>
                        <h1 className='selectionCart'>Carrinho de compras</h1>
                    </div>
                    <br />
                    <hr />
                    <div className="cart-item">
                        <img src={imageBookCart} alt="Capa do Livro" className="book-cover"></img>
                            <div className="book-details">
                                <h3 className="book-title">Título do Livro</h3>
                                <span className="book-price">R$ 49,90</span>
                                <button className="remove-btn">Remover</button>
                            </div>
                    </div>
                </div>
                <div className='cartTotal'>
                    <div className='cartTotalTitle'>
                        <h1 className='totalCart'>Total do carrinho </h1>
                    </div>
                    <br />
                    <hr />
                </div>
            </div>
        </>
    );
}

export default CartShop;