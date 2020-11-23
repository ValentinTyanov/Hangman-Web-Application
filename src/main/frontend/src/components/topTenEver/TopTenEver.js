// import React from "react";
import React, { useEffect, useState } from "react";
import axios from "axios";
import SwitchRanking from "../switchRanking/SwitchRanking";
import RankingTable from "../rankingTable/RankingTable";
import HomeButton from "../homeButton/HomeButton";
// import useTopTenEver from "../useTopTenEver/useTopTenEver";

const TopTenEver = (props) => {
  const [topTenEver, setTopTenEver] = useState([]);

  useEffect(() => {
    axios.get("react-api/topten-ever").then((response) => {
      setTopTenEver(response.data);
    });
  }, []);

  // const [topTenEver, isError] = useTopTenEver();

  // if (isError) {
  //   return <div>Failed to load...</div>;
  // }
  return (
    <>
      <RankingTable curState={topTenEver} />
      <SwitchRanking type="Last 30 Days" url="topten-last-month" />
      {props.homeButton ? <HomeButton /> : null}
    </>
  );
};

export default TopTenEver;
