import { Navigate } from "react-router-dom";
import { useAuth } from "../hooks/useAuth";

interface Props {
  children: JSX.Element;
}

export function AdminRoute({ children }: Props) {
  const { user } = useAuth();

  if (!user) {
    return <Navigate to="/" />;
  }

  return user.role === 'ADMIN' ? children : <Navigate to="/" />;
}
