import React, { useEffect, useState } from 'react'
import axios from 'axios'

function Games() {
  const [gamesList, setGamesList] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/games')
      .then(res => {
        setGamesList(res.data)
      })
      .catch(err => console.log(err))
  }, [])

  console.log(gamesList)

  return (
    <div>
      <h1>Games Data</h1>
      <ol>
        {gamesList.map((game, idx) => (
          <li key={idx}>
            <ul>
              <li>created: {game.created}</li>
              {game.gamePlayers.map((gamePlayer, i) => (
                <li key={i}>
                  player email: {gamePlayer.player.player_email}
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ol>
    </div>
  )
}

export default Games
