import React from 'react'
import TableCell from './TableCell'

function TableRow({
  th,
  tableType,
  toggleCellClass
}) {

  const cellsArr = []
  // 10 cells in a row
  for (let i = 1; i <= 10; i++) { cellsArr.push(i) }

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
