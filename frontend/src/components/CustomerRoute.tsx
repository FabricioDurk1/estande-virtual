import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

interface Props {
  children: JSX.Element;
}

export function CustomerRoute({ children }: Props) {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/" />;
  }

  return user.role === 'CUSTOMER' ? children : <Navigate to="/" />;
}
