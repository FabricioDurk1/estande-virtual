import { Link, Outlet, useLocation } from "react-router-dom";
import "./profile.css";

const sideBarItems = [
  {
    name: "Minha Conta",
    link: "/profile"
  },

  {
    name: "Meu Endereço",
    link: "/profile/myAddress"
  },

  {
    name: "Meus cartões",
    link: "/profile/myCards"
  }
];

export function Profile() {
  const location = useLocation();

  return (
    <main className="profile-page">
      <aside>
        <ul>
          {sideBarItems.map((item) => (
            <li
              key={item.name}
              className={location.pathname === item.link ? "selected" : ""}
            >
              <Link to={item.link}>{item.name}</Link>
            </li>
          ))}
        </ul>
      </aside>

      <Outlet />
    </main>
  );
}
