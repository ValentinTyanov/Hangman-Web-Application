import React from "react";
import { Link } from "react-router-dom";

import "./HomeButton.css";

const HomeButton = () => (
  <div className="HomeButton-Container">
    <Link to="/">
      <input
        type="submit"
        value="Home"
        className="HomeButton-Button"
        style={{ color: "#00FF00" }}
      />
    </Link>
  </div>
);

export default HomeButton;
