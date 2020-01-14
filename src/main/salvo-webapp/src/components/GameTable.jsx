import React from 'react'
import GameTableRow from './GameTableRow'
import '../styles/GameTable.css'

function GameTable() {

  const colsArr = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  return (
    <div className="game-wrapper">
      <h1>Ships Location</h1>

      <table className="game-table">
        <thead>
          <tr>
            {colsArr.map(col => (
              <th>{col}</th>
            ))}
          </tr>
        </thead>
        <tbody>
          {rowsArr.map(row => (
            <GameTableRow key={row} th={row} />
          ))}
        </tbody>

      </table>

    </div>
  )
}

export default GameTable;
