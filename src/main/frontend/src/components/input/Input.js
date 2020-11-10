import React, { useState } from "react";
import axios from "axios";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Input.css";

const Input = () => {
  const [alias, setAlias] = useState("");

  const handleClick = () => {
    axios
      .post(
        "react-api/start-game",
        {
          alias,
        },
        {
          headers: {
            "Content-Type": "application/json; charset=UTF-8",
          },
        }
      )
      .then((response) => {
        window.location.replace(`/games/${response.data}`);
      })
      .catch((error) => {
        console.log(error.response);
      });
  };

  return (
    <Form>
      <Form.Group className="Input-Form">
        <Form.Control
          type="text"
          maxLength="15"
          placeholder="Enter Alias"
          onChange={(event) => setAlias(event.target.value)}
        />
      </Form.Group>
      <Button onClick={handleClick} variant="success" size="lg">
        Start New Game
      </Button>
    </Form>
  );
};

export default Input;
