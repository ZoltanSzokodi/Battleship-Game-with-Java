import React from 'react'
import TableCell from './TableCell'

function TableRow({
  th,
  playerSalvos,
  playerShips,
  opponentSalvos,
  tableType
}) {
  // const cellsLength = Array.from(Array(10).keys());
  const cellsArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

  return (
    <tr>
      <th>{th}</th>
      {cellsArr.map(cell => (
        <TableCell
          key={th + cell}
          id={th + cell}
          tableType={tableType}
          playerShips={playerShips}
          playerSalvos={playerSalvos}
          opponentSalvos={opponentSalvos}
        />
      ))}
    </tr>
  )
}

export default TableRow;
