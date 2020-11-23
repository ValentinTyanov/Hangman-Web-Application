import React, { Suspense } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import HomePage from "./containers/homePage/HomePage";
import "./App.css";

const TopTenEver = React.lazy(() =>
  import("./components/topTenEver/TopTenEver")
);

const TopTenLastMonth = React.lazy(() =>
  import("./components/topTenLastMonth/TopTenLastMonth")
);

const Victory = React.lazy(() => import("./containers/victory/Victory"));

const Defeat = React.lazy(() => import("./containers/defeat/Defeat"));

const Login = React.lazy(() => import("./containers/login/Login"));

const App = () => (
  <div className="App">
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={HomePage} />
        <Route
          path="/topten-ever"
          render={() => (
            <Suspense fallback={<div>Loading...</div>}>
              <TopTenEver homeButton />
            </Suspense>
          )}
        />
        <Route
          path="/topten-last-month"
          render={() => (
            <Suspense fallback={<div>Loading...</div>}>
              <TopTenLastMonth />
            </Suspense>
          )}
        />
        <Route
          path="/victory"
          render={() => (
            <Suspense fallback={<div>Loading...</div>}>
              <Victory />
            </Suspense>
          )}
        />
        <Route
          path="/defeat"
          render={() => (
            <Suspense fallback={<div>Loading...</div>}>
              <Defeat />
            </Suspense>
          )}
        />
        <Route
          path="/login/:gameId"
          render={() => (
            <Suspense fallback={<div>Loading...</div>}>
              <Login />
            </Suspense>
          )}
        />
      </Switch>
    </BrowserRouter>
  </div>
);

export default App;
