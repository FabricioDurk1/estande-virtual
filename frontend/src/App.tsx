import React, { useState } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css'

import { Head } from './components/Head';
import Login from './pages/login/Login';
import Register from './pages/register/Register';
import Cards from './pages/cards/Cards';
import CartShop from './pages/cartShop/Cart';
import { Profile } from './pages/profile/Profile';
import { User } from './models/User';
import { BookDescription } from './pages/bookDescription/BookDescription';
import { BookRegister } from './pages/bookRegister/BookRegister';
import { AuthorRegister } from './pages/authorRegister/AuthorRegister';
import { EditorRegister } from './pages/editorRegister/EditorRegister';
import { Admnistrator } from './pages/admnistrator/admnistrator';

const App: React.FC = () => {
  const [isSigned, setIsSigned] = useState(false)
  const [user, setUser] = useState<User | null>(null)

  function onLogin(responseUser: User){
    setIsSigned(true)
    setUser(responseUser)
  }

  function logout(){
    setIsSigned(false)
    setUser(null)
  }

  return (
    <BrowserRouter> 
      <Head isSigned={isSigned} username={user?.name || ""} logout={logout} />
      <Routes>
        <Route path="/login" Component={() => <Login onSuccessLogin={onLogin} />} />
        <Route path="/register" Component={Register} />
        <Route path="/" Component={Cards} />
        <Route path="/cartShop" Component={CartShop} />
        <Route path="/bookDescription" Component={BookDescription} />
        <Route path="/bookRegister" Component={BookRegister} />
        <Route path="/authorRegister" Component={AuthorRegister} />
        <Route path="/editorRegister" Component={EditorRegister} />
        <Route path="/adm" Component={Admnistrator} />
        <Route path="/profile" Component={() => <Profile user={user} />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App;
