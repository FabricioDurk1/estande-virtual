import './cards.css'
import imageLivro from '../../assets/images/bikeguy.svg'
// import imageLivro2 from '../../assets/images/brasCubas.webp'
import BookCard from '../../components/BookCard';
import { Link } from 'react-router-dom';

const books = [
  {
    price: 39.90,
    coverImage: imageLivro
  },
  {
    price: 39.90,
    coverImage: imageLivro
  },
  {
    price: 49.90,
    coverImage: imageLivro
  },
  {
    price: 60.90,
    coverImage: imageLivro
  },
  {
    price: 39.90,
    coverImage: imageLivro
  },
  {
    price: 39.90,
    coverImage: imageLivro
  },
  {
    price: 49.90,
    coverImage: imageLivro
  },
  {
    price: 60.90,
    coverImage: imageLivro
  },
  {
    price: 39.90,
    coverImage: imageLivro
  }
] 

function Cards() {

  return (
      <div className='cards'>
        <div className='general-books'>
        {
          books.map((book) => {
            return(
              <BookCard price={book.price} coverImage={book.coverImage}/>
            )
          })
        }
        
        <div className='addLivro'>
        <Link to={'./bookRegister'}>
         <span className='plusSignal'>&#43;</span>
         </Link>  
        </div>
        </div>
      </div>
  );
}

export default Cards;
