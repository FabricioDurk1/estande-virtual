import "./loader.css";

interface Props {
  color?: string;
}

export function Loader({ color = "#2B5A71" }: Props) {
  return (
    <div className="loader-container">
      <div className="loader" style={{ background: color }}></div>
    </div>
  )
}