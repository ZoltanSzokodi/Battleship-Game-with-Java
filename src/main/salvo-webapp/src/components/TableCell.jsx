import React from 'react'
import '../styles/TableCell.css'

function GameTableCell({ id, gameViewObj }) {
  let shipLocations = [];
  gameViewObj.ships.forEach(ship => (
    shipLocations.push(...ship.location)
  ))

  return <td className="table-cell">
    {shipLocations.includes(id) && "X"}
  </td>;
}

export default GameTableCell;
