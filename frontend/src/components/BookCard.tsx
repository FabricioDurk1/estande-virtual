import './bookCard.css'
import { useState } from 'react';
import { Link } from 'react-router-dom';



interface BookCardProps {
  price: number;
  coverImage: string;
}



function BookCard(props: BookCardProps) {
  
  const [clicked, setClicked] = useState(false)

  function permanentClick(){
    if (clicked == true) {
      setClicked(false)
    } else {
      setClicked(true)
    }
  }


  return (
    <div className='individual-cards'>
      <div className='card-book'>
        <img className='book-cover' src={props.coverImage} alt="bikeguy" />
        <div className='label-price'>
          <p>R$ {props.price}</p><div className='vertical-line'></div>
          <Link to="/">
            <button onClick={permanentClick} className={clicked == true ? 'active cart-icon' : 'cart-icon'}>
              <svg className='cart' xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><circle cx="176" cy="416" r="32" fill="currentColor" /><circle cx="400" cy="416" r="32" fill="currentColor" /><path fill="currentColor" d="M456.8 120.78a23.92 23.92 0 0 0-18.56-8.78H133.89l-6.13-34.78A16 16 0 0 0 112 64H48a16 16 0 0 0 0 32h50.58l45.66 258.78A16 16 0 0 0 160 368h256a16 16 0 0 0 0-32H173.42l-5.64-32h241.66A24.07 24.07 0 0 0 433 284.71l28.8-144a24 24 0 0 0-5-19.93" /></svg>
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default BookCard;
