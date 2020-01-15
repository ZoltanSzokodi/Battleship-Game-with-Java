import React from 'react'
import '../styles/TableCell.css'

function GameTableCell({ id, gridType, gameViewObj }) {
  // salvo or ship depending on the conditional
  let locations = [];

  if (gridType === "ship") {
    gameViewObj.ships.forEach(ship => (
      locations.push(...ship.location)
    ))
  } else {
    gameViewObj.salvoes.forEach(salvo => (
      locations.push(...salvo.location)
    ))
  }

  return <td className="table-cell">
    {locations.includes(id) && "X"}
  </td>;
}

export default GameTableCell;
