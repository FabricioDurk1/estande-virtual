import { createContext, useState } from "react";

import { User } from "../models/User"
import { api } from "../services/api";
import { Address } from "../models";

type AuthContextData = {
  user: User | null;
  isSigned: boolean;
  login: (email: string, password: string) => Promise<{ role: string; }>;
  updateUser: (user: User) => void;
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
  zipCode: string;
  state: string;
  neighborhood: string;
  street: string;
  number: string;
  complement: string;
  city: string;
}

type ApiLoginResponse = {
  user: ApiUser;
  address: ApiAddress;
  
  accessToken: string;
}

export function AuthContextProvider({ children }: AuthContextProviderProps) {
  const [user, setUser] = useState<User | null>(null);

  function logout() {
    setUser(null);
  }

  async function login(email: string, password: string){
    const requestBody = {
      email: email,
      password: password
    }

    const response = await api.post<ApiLoginResponse>("/auth/login", requestBody);
    const { accessToken, user, address } = response.data;

    let userAddress: Address | null = null;

    if (address) {
      userAddress = {
        postalCode: address.zipCode,
        state: address.state,
        neighborhood: address.neighborhood,
        street: address.street,
        addressNumber: address.number,
        complement: address.complement,
        city: address.city
      }
    }

    setUser({
      name: user.name,
      email: user.email,
      role: user.role,
      birthDate: user.birthDate,
      cpf: user.cpf,
      phone: user.phone,
      address: userAddress
    });

    api.defaults.headers.common.Authorization = `Bearer ${accessToken}`;

    return {
      role: user.role
    }
  }

  function updateUser(user: User) {
    setUser(user);
  }

  return (
    <AuthContext.Provider value={{ user, login, updateUser, isSigned: !!user, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
