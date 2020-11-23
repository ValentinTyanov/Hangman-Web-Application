/* eslint-disable nonblock-statement-body-position */
import React, { useState } from "react";
// import axios from "axios";
import { useParams } from "react-router-dom";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const { gameId } = useParams();

  const handleClick = () => {
    fetch("/react-api/authentication", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email,
        password,
        gameId,
      }),
    }).then((response) => {
      if (response.status === 200) {
        window.location.replace(`/games/${gameId}/real-word`);
      } else if (response.status === 401) {
        setError("Invalid credentials");
      }
    });
  };

  return (
    <div className="Login-Page">
      Admin Access
      <Form>
        <Form.Group className="Login-Email">
          <Form.Control
            type="email"
            maxLength="25"
            placeholder="Enter Email"
            onChange={(event) => setEmail(event.target.value)}
          />
        </Form.Group>

        <Form.Group className="Login-Password">
          <Form.Control
            type="password"
            maxLength="25"
            placeholder="Password"
            onChange={(event) => setPassword(event.target.value)}
          />
        </Form.Group>
        <Button onClick={handleClick} variant="success" size="lg">
          Login
        </Button>
      </Form>
      <h2 className="Login-Error">{error}</h2>
    </div>
  );
};

export default Login;
