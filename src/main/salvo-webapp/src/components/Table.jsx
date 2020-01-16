import React from 'react'
import TableRow from './TableRow'
import { withStyles } from '@material-ui/styles';

const styles = {
  table: {
    width: "50rem",
    height: "50rem",
    textAlign: "center",
    borderCollapse: "collapse"
  }
}

function Table({ gameViewObj, tableType, classes }) {

  const colsArr = ["", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  return (
    <React.Fragment>
      <table className={classes.table}>

        <caption>{tableType === "ship" ? "SHIP GRID" : "SALVO GRID"}</caption>

        <thead>
          <tr>
            {colsArr.map(col => (
              <th key={col}>{col}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {rowsArr.map(row => (
            <TableRow key={row} th={row} tableType={tableType} gameViewObj={gameViewObj} />
          ))}
        </tbody>

      </table>
    </React.Fragment>
  )
}

export default withStyles(styles)(Table);
