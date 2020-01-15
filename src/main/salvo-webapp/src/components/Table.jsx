import React from 'react'
import TableRow from './TableRow'
import '../styles/Table.css'

function Table({ gameViewObj, gridType }) {

  const colsArr = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  return (
    <React.Fragment>
      <table className="player-table">

        <caption>{gridType === "ship" ? "SHIP GRID" : "SALVO GRID"}</caption>

        <thead>
          <tr>
            {colsArr.map(col => (
              <th key={col}>{col}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {rowsArr.map(row => (
            <TableRow key={row} th={row} gridType={gridType} gameViewObj={gameViewObj} />
          ))}
        </tbody>

      </table>
    </React.Fragment>
  )
}

export default Table;
