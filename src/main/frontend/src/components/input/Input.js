import React, { useState } from "react";
import axios from "axios";
// import useSWR from "swr";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./Input.css";

const Input = () => {
  const [alias, setAlias] = useState("");

  const handleClick = () => {
    axios
      .post(
        "/react-api/start-game",
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
      });
  };

  // const handleClick = () => {
  // const { data, error } = useSWR("/react-api/start-game", fetcher);

  // const fetcher = (url) =>
  //   axios
  //     .post(
  //       url,
  //       {
  //         alias,
  //       },
  //       {
  //         headers: {
  //           "Content-Type": "application/json; charset=UTF-8",
  //         },
  //       }
  //     )
  //     .then((response) => response.data);

  // if (error) return <div>{error.message}</div>;
  // if (!data) return <div>Loading...</div>;
  // return window.location.replace(`/games/${data}`);
  // };

  // const handleClick = () => {
  //   fetch("/react-api/start-game", {
  //     method: "post",
  //     headers: {
  //       "Content-Type": "application/json",
  //     },
  //     body: JSON.stringify({
  //       alias,
  //     }),
  //   })
  //     .then((res) => res.json())
  //     .then((result) => {
  //       window.location.replace(`/games/${result}`);
  //     });
  // };

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
