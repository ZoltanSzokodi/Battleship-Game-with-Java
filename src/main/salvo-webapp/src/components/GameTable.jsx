import React, { useEffect, useState } from 'react'
import axios from 'axios'
import GameTableRow from './GameTableRow'
import '../styles/GameTable.css'

function GameTable() {

  function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
    let results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }

  const gp = getParameterByName('gp');

  const [gameView, setGameView] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/game_view/${gp}`)
      .then(res => {
        setGameView(res.data)
        setLoading(loading => !loading)
      })
      .catch(err => console.log(err))
  }, [])

  console.log(loading)

  const colsArr = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  return (
    <div className="game-wrapper">
      <h1>Ships Location</h1>

      {!loading && <table className="game-table">
        <thead>
          <tr>
            {colsArr.map(col => (
              <th key={col}>{col}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {rowsArr.map(row => (
            <GameTableRow key={row} th={row} gameView={gameView} />
          ))}
        </tbody>

      </table>}


    </div>
  )
}

export default GameTable;
