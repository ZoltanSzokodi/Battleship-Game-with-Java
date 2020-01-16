import React from 'react'
import { withStyles } from '@material-ui/styles';

const styles = {
  tableCell: {
    border: "1px solid black",
    width: "10%",
    height: "10%"
  },
  shipLocation: {
    backgroundColor: "Navy",
  },
  salvoLocation: {
    backgroundColor: "LightSkyBlue"
  },
  shipHit: {
    backgroundColor: "LightCoral"
  },
  shipMiss: {
    backgroundColor: "LightBlue"
  }
}

function GameTableCell({
  id,
  tableType,
  playerSalvos,
  playerShips,
  opponentSalvos,
  classes
}) {

  const {
    tableCell,
    shipLocation,
    salvoLocation,
    shipHit,
    shipMiss
  } = classes;

  function toggleTableCellClasses(typeOfTable) {

    if (typeOfTable === "ship") {

      if (playerShips.includes(id)) {
        // show player ship's location grid which has been hit by opponent
        if (opponentSalvos.includes(id)) return shipHit;
        // show player ships
        return shipLocation;

      } else if (!playerShips.includes(id)) {
        // show missed shots of opponent on player's table
        if (opponentSalvos.includes(id)) return shipMiss;
      }
      return tableCell;

    } else if (typeOfTable === "salvo") {

      // show player's salvos on opponent's table
      if (playerSalvos.includes(id)) return salvoLocation;
      return tableCell;
    }
  }

  return <td className={toggleTableCellClasses(tableType)}></td>;
}

export default withStyles(styles)(GameTableCell);
