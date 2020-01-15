import React from 'react'
import TableCell from './TableCell'

function TableRow({ th, gameViewObj, gridType }) {
  // const cellsLength = Array.from(Array(10).keys());
  const cellsArr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

  return (
    <tr>
      <th>{th}</th>
      {cellsArr.map(cell => (
        <TableCell key={th + cell} id={th + cell} gridType={gridType} gameViewObj={gameViewObj} />
      ))}
    </tr>
  )
}

export default TableRow;
