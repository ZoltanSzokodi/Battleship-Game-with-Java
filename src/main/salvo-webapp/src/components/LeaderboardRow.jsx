import React from 'react'
import { uuid } from 'uuidv4'
import { withStyles } from '@material-ui/styles';

const styles = {
  tableData: {
    textAlign: "center",
    border: "1px solid black"
  }
};

function LeaderboardRow({ rowObj, th, classes }) {

  const tableDataArr = Object.values(rowObj);

  return (
    <tr>
      <th>{th}</th>
      {tableDataArr.map(td => <td className={classes.tableData} key={uuid()}>{td}</td>)}
    </tr>
  );
}

export default withStyles(styles)(LeaderboardRow);
