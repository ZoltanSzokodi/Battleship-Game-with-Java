import React from 'react'
import LeaderboardRow from './LeaderboardRow'
import { withStyles } from '@material-ui/styles';
import { uuid } from 'uuidv4'

const styles = {
  board: {
    width: "60%",
    height: "auto",
    border: "1px solid black",
    borderCollapse: "collapse",
    fontSize: "1.6rem"
  },
  '@global': {
    th: {
      width: "16.6666%",
      padding: "1rem",
      border: "1px solid black",
    }
  }
};

function Leaderboard({ leaderboardList, classes }) {
  let sortedLeaderboardList = leaderboardList.sort((a, b) => b.totalPoints - a.totalPoints);

  return (
    <table className={classes.board}>
      <thead>
        <tr>
          <th>Position</th>
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
  );
}

export default withStyles(styles)(Leaderboard);
