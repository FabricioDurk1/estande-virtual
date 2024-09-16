import {
  BrowserRouter,
  Route,
  Routes as ReactRouterDomRoutes
} from "react-router-dom";
import "./App.css";

import { AuthorList } from "./pages/AuthorList/AuthorList";
import { BookList } from "./pages/bookList/BookList";
import { BookEdit } from "./pages/bookEdit/BookEdit";
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
import { CreditCardRegister } from "./pages/creditCardRegister/CreditCardRegister";
import { CreditCardUpdate } from "./pages/creditCardUpdate/CreditCardUpdate";
import { AdminRoute } from "./components/AdminRoute";
import { CustomerRoute } from "./components/CustomerRoute";
import { Head } from "./components/Head";

export function Routes() {
  return (
    <BrowserRouter>
      <Head />
      <ReactRouterDomRoutes>
        <Route index path="/" element={<Cards />} />
        <Route path="/cartShop" element={<CartShop />} />
        <Route path="/bookDescription/:bookId" element={<BookDescription />} />

        <Route path="/login" element={<Login/>} />
        <Route path="/register" element={<Register/>} />

        <Route
          path="/adm"
          element={
            <AdminRoute>
              <Admnistrator />
            </AdminRoute>
          }
        />

        <Route path="/bookRegister" element={<AdminRoute><BookRegister /></AdminRoute>} />
        <Route path="/authorRegister" element={<AdminRoute><AuthorRegister/></AdminRoute>} />
        <Route path="/editorRegister" element={<AdminRoute><EditorRegister/></AdminRoute>} />
        <Route path="/editorUpdate/:publisherId" element={<AdminRoute><EditorUpdate/></AdminRoute>} />
        <Route path="/editorList" element={<AdminRoute><EditorList/></AdminRoute>} />

        <Route path="/authorList" element={<AdminRoute><AuthorList/></AdminRoute>} />
        <Route path="/bookList" element={<AdminRoute><BookList/></AdminRoute>} />
        <Route path="/bookEdit" element={<AdminRoute><BookEdit/></AdminRoute>} />

        <Route path="/profile" element={<CustomerRoute><Profile/></CustomerRoute>}>
          <Route path="/profile" index element={<CustomerRoute><MyAccount/></CustomerRoute>} />
          <Route path="/profile/myAddress" element={<CustomerRoute><MyAddress/></CustomerRoute>} />
          <Route path="/profile/myCards" element={<CustomerRoute><MyCards/></CustomerRoute>} />
        </Route>

        <Route path="/creditCardRegister" element={<CustomerRoute><CreditCardRegister/></CustomerRoute>} />
        <Route
          path="/creditCardUpdate/:creditCardId"
          element={<CustomerRoute><CreditCardUpdate/></CustomerRoute>}
        />
      </ReactRouterDomRoutes>
    </BrowserRouter>
  );
}
