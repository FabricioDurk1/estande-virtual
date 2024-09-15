import { createContext, useState } from "react";
import { User } from "../models/User"
import { api } from "../services/api";

type AuthContextData = {
  user: User | null;
  isSigned: boolean;
  login: (email: string, password: string) => Promise<void>;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextData>({} as AuthContextData);

interface AuthContextProviderProps {
  children: React.ReactNode;
}

type ApiUser = {
  name: string;
  email: string;
  role: string;
  cpf: string;
  phone: string;
  birthDate: string;
}

type ApiAddress = {
  postalCode: string;
  state: string;
  neighborhood: string;
  street: string;
  addressNumber: string;
  complement: string;
}
  

type ApiLoginResponse = {
  user: ApiUser;
  address: ApiAddress;
  
  accessToken: string;
}

export function AuthContextProvider({ children }: AuthContextProviderProps) {
  const [user, setUser] = useState<User | null>({
    name: "Jorge",
    email: "jorge@email.com",
    role: "ADASD",
    cpf: "12345678901",
    phone: "88992146067",
    birthDate: "2003-02-01",
    address: {
      postalCode: "12345678",
      state: "SP",
      neighborhood: "Vila Nova",
      street: "Av. das Nações Unidas",
      addressNumber: "123",
      complement: "Casa"
    }
  });

  function logout() {
    setUser(null);
  }

  async function login(email: string, password: string){
    const requestBody = {
      email: email,
      password: password
    }

    const response = await api.post<ApiLoginResponse>("/auth/login", requestBody);
    const { accessToken, address, user } = response.data;

    setUser({
      name: user.name,
      email: user.email,
      role: user.role,
      birthDate: user.birthDate,
      cpf: user.cpf,
      phone: user.phone,
      address: address
    });

    api.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
  }

  return (
    <AuthContext.Provider value={{ user, login, isSigned: !!user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
