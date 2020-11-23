/* eslint-disable no-restricted-syntax */
import React from "react";

import "./RankingTable.css";

const RankingTable = (props) => (
  <>
    <div className="RankingTable-Title">
      <h1>Ranking</h1>
    </div>

    <table className="RankingTable-Table">
      <thead>
        <tr>
          <td>Alias</td>
          <td>Score</td>
        </tr>
      </thead>
      <tbody>
        {props.curState.map((ranking) => (
          <tr key={ranking.alias}>
            <td>{ranking.alias}</td>
            <td className="RankingTable-ScoreCell">{ranking.highScore}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </>
);
export default RankingTable;
