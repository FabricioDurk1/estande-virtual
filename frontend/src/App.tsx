import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css'
import Head from './components/Head';
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Cards from './pages/cards/Cards';
import CartShop from './pages/cartShop/Cart';


const App: React.FC = () => {
  return (
    <BrowserRouter> 
      <Head />
      <Routes>
        <Route path="/login" Component={Login} />
        <Route path="/register" Component={Register} />
        <Route path="/" Component={Cards} />
        <Route path="/cartShop" Component={CartShop} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
