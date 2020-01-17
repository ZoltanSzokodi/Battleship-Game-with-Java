import React from 'react'
import TableCell from './TableCell'

function TableRow({
  th,
  tableType,
  toggleCellClass
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
          toggleCellClass={toggleCellClass}
        //showTurn={showTurn}
        />)
      )}
    </tr>
  )
}

export default TableRow;
