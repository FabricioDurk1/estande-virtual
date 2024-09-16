import React from "react";
import "./App.css";

import { AuthContextProvider } from "./contexts/AuthContext";
import { CartContextProvider } from "./contexts/CartContext";
import { Routes } from "./Routes";

const App: React.FC = () => {
  return (
    <CartContextProvider>
      <AuthContextProvider>
        <Routes />
      </AuthContextProvider>
    </CartContextProvider>
  );
};

export default App;
