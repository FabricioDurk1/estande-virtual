import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";

import { Head } from "./components/Head";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Cards from "./pages/cards/Cards";
import CartShop from "./pages/cartShop/Cart";
import { Profile } from "./pages/profile/Profile";
import { BookDescription } from "./pages/bookDescription/BookDescription";
import { BookRegister } from "./pages/bookRegister/BookRegister";
import { AuthorRegister } from "./pages/authorRegister/AuthorRegister";
import { EditorRegister } from "./pages/editorRegister/EditorRegister";
import { Admnistrator } from "./pages/admnistrator/admnistrator";
import { EditorList } from "./pages/editorList/EditorList";
import { EditorUpdate } from "./pages/editorUpdate/EditorUpdate";
import { MyAccount, MyAddress, MyCards } from "./pages/profile/components";
import { AuthContextProvider } from "./contexts/AuthContext";
import { CreditCardRegister } from "./pages/creditCardRegister/CreditCardRegister";
import { CreditCardUpdate } from "./pages/creditCardUpdate/CreditCardUpdate";
import { CartContextProvider } from "./contexts/CartContext";

const App: React.FC = () => {
  return (
    <CartContextProvider>
      <AuthContextProvider>
        <BrowserRouter>
          <Head />
          <Routes>
            <Route path="/login" Component={Login} />
            <Route path="/register" Component={Register} />
            <Route path="/" Component={Cards} />
            <Route path="/cartShop" Component={CartShop} />
            <Route path="/bookDescription/:bookId" Component={BookDescription} />
            <Route path="/bookRegister" Component={BookRegister} />
            <Route path="/authorRegister" Component={AuthorRegister} />
            <Route path="/editorRegister" Component={EditorRegister} />
            <Route path="/editorUpdate/:publisherId" Component={EditorUpdate} />
            <Route path="/editorList" Component={EditorList} />
            <Route path="/adm" Component={Admnistrator} />

            <Route path="/creditCardRegister" Component={CreditCardRegister} />
            <Route path="/creditCardUpdate/:creditCardId" Component={CreditCardUpdate} />

            <Route path="/profile" Component={Profile}>
              <Route path="/profile" index Component={MyAccount} />
              <Route path="/profile/myAddress" Component={MyAddress} />
              <Route path="/profile/myCards" Component={MyCards} />
            </Route>
          </Routes>
        </BrowserRouter>
    </AuthContextProvider>
    </CartContextProvider>
  );
};

export default App;
