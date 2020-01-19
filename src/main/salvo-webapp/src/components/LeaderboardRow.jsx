import React from 'react'
import { uuid } from 'uuidv4'

function LeaderboardRow({ rowObj, th }) {

  const tableDataArr = Object.values(rowObj);

  return (
    <tr>
      <th>{th}</th>
      {tableDataArr.map(td => <td key={uuid()}>{td}</td>)}
    </tr>
  )
}

export default LeaderboardRow;
