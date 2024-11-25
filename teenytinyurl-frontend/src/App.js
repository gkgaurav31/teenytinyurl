import React, { useState } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [longURL, setLongURL] = useState("");
  const [shortURL, setShortURL] = useState("");

  const backendHost = "https://teenytinyurl-backend.azurewebsites.net";

  const shortenURL = async () => {
    const response = await axios.post(
      "https://teenytinyurl-backend.azurewebsites.net/api/shorten",
      {
        longURL,
      }
    );
    setShortURL(response.data.shortUrl);
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h1>URL Shortener</h1>
      https://
      <input
        type="text"
        placeholder="Enter long URL"
        value={longURL}
        onChange={(e) => setLongURL(e.target.value)}
      />
      <button onClick={shortenURL}>Shorten</button>
      {shortURL && (
        <p>
          Short URL:{" "}
          <a
            href={`${backendHost}/${shortURL}`}
            target="_blank"
            rel="noopener noreferrer"
          >
            {`${backendHost}/${shortURL}`}
          </a>
        </p>
      )}
    </div>
  );
}

export default App;
