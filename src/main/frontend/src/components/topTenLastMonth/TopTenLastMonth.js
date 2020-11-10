import React, { useState, useEffect } from "react";
import axios from "axios";
import SwitchRanking from "../switchRanking/SwitchRanking";
import RankingTable from "../rankingTable/RankingTable";
import HomeButton from "../homeButton/HomeButton";

const TopTenEver = () => {
  const [topTenLastMonth, setTopTenLastMonth] = useState([]);

  useEffect(() => {
    axios.get("react-api/topten-last-month").then((response) => {
      setTopTenLastMonth(response.data);
    });
  }, []);

  return (
    <>
      <RankingTable curState={topTenLastMonth} />
      <SwitchRanking type="All-time" url="topten-ever" />
      <HomeButton />
    </>
  );
};

export default TopTenEver;
