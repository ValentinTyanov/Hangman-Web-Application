import React, { useState, useEffect } from "react";
import axios from "axios";
import SwitchRanking from "../switchRanking/SwitchRanking";
import RankingTable from "../rankingTable/RankingTable";
import HomeButton from "../homeButton/HomeButton";

const TopTenEver = (props) => {
  const [topTenEver, setTopTenEver] = useState([]);

  useEffect(() => {
    axios.get("react-api/topten-ever").then((response) => {
      setTopTenEver(response.data);
    });
  }, []);

  return (
    <>
      <RankingTable curState={topTenEver} />
      <SwitchRanking type="Last 30 Days" url="topten-last-month" />
      {props.homeButton ? <HomeButton /> : null}
    </>
  );
};

export default TopTenEver;
