import React from "react";
import TopTenEver from "../../components/topTenEver/TopTenEver";
import "./Defeat.css";

const Defeat = () => (
  <>
    <div>
      <h1 className="Defeat-Title"> Game Over </h1>
    </div>
    <TopTenEver homeButton />
  </>
);

export default Defeat;
