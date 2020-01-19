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
  classes,
  toggleCellClass
}) {

  const {
    tableCell,
    shipLocation,
    salvoLocation,
    shipHit,
    shipMiss
  } = classes;

  return <td className={
    toggleCellClass(tableType, shipHit, shipMiss, shipLocation, salvoLocation, tableCell, id)}>
  </td>;
}

export default withStyles(styles)(GameTableCell);
