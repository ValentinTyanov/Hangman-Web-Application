import React from "react";
import TopTenEver from "../../components/topTenEver/TopTenEver";
import "./Victory.css";

const Victory = () => (
  <>
    <div>
      <h1 className="Victory-Title">You won!</h1>
    </div>
    <TopTenEver homeButton />
  </>
);

export default Victory;
