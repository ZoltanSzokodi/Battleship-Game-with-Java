import React from 'react'
import { withStyles } from '@material-ui/styles';

const styles = {
  tableCell: {
    border: "1px solid black",
    width: "10%",
    height: "10%"
  },
  shipLocation: {
    backgroundColor: "gray",
  },
  salvoLocation: {
    backgroundColor: "CornflowerBlue"
  },
  shipHit: {
    backgroundColor: "Crimson"
  }
}

function GameTableCell({ id, tableType, gameViewObj, classes }) {

  function toggleTableCellClasses(typeOfTable) {
    const { tableCell, shipLocation, salvoLocation, shipHit } = classes;

    if (typeOfTable === "ship") {
      let myShips = [];
      let opponentSalvos = [];

      gameViewObj.ships.forEach(ship => (
        myShips.push(...ship.location)
      ))
      gameViewObj.opponent_info.opponent_salvos.forEach(salvo => (
        opponentSalvos.push(...salvo.location)
      ))

      if (myShips.includes(id)) {
        if (opponentSalvos.includes(id)) {
          return shipHit;
        }
        return shipLocation;
      }
      return tableCell;
    } else {
      let mySalvos = [];

      gameViewObj.salvos.forEach(salvo => (
        mySalvos.push(...salvo.location)
      ))

      if (mySalvos.includes(id)) {
        return salvoLocation;
      }
      return tableCell;
    }
  }

  return <td className={toggleTableCellClasses(tableType)}></td>;
}

export default withStyles(styles)(GameTableCell);
