import React from 'react'
import '../styles/GameTableCell.css'

function GameTableCell({ id, gameView }) {
  let shipLocaions = [];
  gameView.gamePlayers[0].player.player_ships.forEach(ship => (
    ship.location.forEach(location => shipLocaions.push(location))
  ))

  return <td className={shipLocaions.includes(id) ? "table-cell occupied" : "table-cell"}></td>;
}

export default GameTableCell;
