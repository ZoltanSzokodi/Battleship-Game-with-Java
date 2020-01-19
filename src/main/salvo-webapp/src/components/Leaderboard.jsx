import React from 'react'
import LeaderboardRow from './LeaderboardRow'
import { withStyles } from '@material-ui/styles';
import { uuid } from 'uuidv4'

const styles = {}

function Leaderboard({ leaderboardList, classes }) {

  let sortedLeaderboardList = leaderboardList.sort((a, b) => b.totalPoints - a.totalPoints);

  console.log(sortedLeaderboardList)

  return (
    <table>

      <thead>
        <tr>
          <th>Player name</th>
          <th>Total points</th>
          <th>Games won</th>
          <th>Games lost</th>
          <th>Tied games</th>
        </tr>
      </thead>

      <tbody>
        {sortedLeaderboardList.map((rowObj, i) => (
          <LeaderboardRow key={uuid()} th={i + 1} rowObj={rowObj} />
        ))}
      </tbody>

    </table>
  )
}

export default withStyles(styles)(Leaderboard);
