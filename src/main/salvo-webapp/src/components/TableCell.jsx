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
  },
  shipMiss: {
    backgroundColor: "AliceBlue"
  }
}

function GameTableCell({ id, tableType, gameViewObj, classes }) {
  const { tableCell, shipLocation, salvoLocation, shipHit, shipMiss } = classes;

  let ships = [];
  let salvos = [];

  function toggleTableCellClasses(typeOfTable) {

    if (typeOfTable === "ship") {

      gameViewObj.ships.forEach(ship => (
        ships.push(...ship.location)
      ))
      gameViewObj.opponent_info.opponent_salvos.forEach(salvo => (
        salvos.push(...salvo.location)
      ))

      if (ships.includes(id)) {
        if (salvos.includes(id)) {
          return shipHit;
        }
        return shipLocation;
      } else if (!ships.includes(id)) {
        if (salvos.includes(id)) {
          return shipMiss;
        }
      }
      return tableCell;

    } else if (typeOfTable === "salvo") {

      gameViewObj.salvos.forEach(salvo => (
        salvos.push(...salvo.location)
      ))

      if (salvos.includes(id)) {
        return salvoLocation;
      }
      return tableCell;
    }
  }
  return <td className={toggleTableCellClasses(tableType)}></td>;
}

export default withStyles(styles)(GameTableCell);
