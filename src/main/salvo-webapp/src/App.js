import React, { useEffect, useState } from 'react'
import './App.css';

function App() {
  const [games, setGames] = useState({});
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const fetchAllGames = async () => {
      const url = "http://localhost:8080/api/games";
      try {
        let response = await fetch(url);
        let data = await response.json();

        setGames(data.games)
        setIsLoading(isLoading => isLoading = !isLoading)
      } catch (err) { console.log(err) }
    }
    fetchAllGames()
  }, [])

  console.log(games)
  console.log(isLoading)
  return (
    <div>
      <h1>Games Data</h1>
      <ol>
        {!isLoading && games.map((game, idx) => (
          <li key={idx}>
            <ul>
              <li>created: {game.created}</li>
              {game.gamePlayers.map((gamePlayer, i) => (
                <li key={i}>player email: {gamePlayer.player.player_email}</li>
              ))}
            </ul>
          </li>
        ))}
      </ol>
    </div>
  );
}

export default App;
