import React from 'react'
import TableRow from './TableRow'
import '../styles/Table.css'

function Table({ gameViewObj }) {

  const colsArr = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  return (
    <React.Fragment>
      <h2>Player: {gameViewObj.player_name} Opponent: {gameViewObj.opponent_info.opponent_name}</h2>

      <table className="player-table">

        <thead>
          <tr>
            {colsArr.map(col => (
              <th key={col}>{col}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {rowsArr.map(row => (
            <TableRow key={row} th={row} gameViewObj={gameViewObj} />
          ))}
        </tbody>

      </table>
    </React.Fragment>
  )
}

export default Table;
