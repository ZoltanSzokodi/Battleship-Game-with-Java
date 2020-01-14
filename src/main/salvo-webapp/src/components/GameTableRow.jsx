import React from 'react'
import GameTableCell from './GameTableCell'

function GameTableRow({ th }) {
  // const cellsLength = Array.from(Array(10).keys());
  const cellsArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
  return (
    <tr>
      <th>{th}</th>
      {cellsArr.map(cell => (
        <GameTableCell key={th + cell} id={th + cell} />
      ))}
    </tr>
  )
}

export default GameTableRow;
