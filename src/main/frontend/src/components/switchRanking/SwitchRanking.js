import React from "react";
import { Link } from "react-router-dom";

import "./SwitchRanking.css";

const SwitchRanking = (props) => (
  <div className="SwitchRanking-RankingType">
    <Link to={props.url}>
      <input type="checkbox" />
      <span className="SwitchRanking-Text">Top 10 {props.type}</span>
    </Link>
  </div>
);

export default SwitchRanking;
