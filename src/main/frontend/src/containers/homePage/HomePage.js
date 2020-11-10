import React from "react";
import TopTenEver from "../../components/topTenEver/TopTenEver";
import Input from "../../components/input/Input";

const HomePage = () => (
  <div>
    <TopTenEver homeButton={false} />
    <Input />
  </div>
);

export default HomePage;
