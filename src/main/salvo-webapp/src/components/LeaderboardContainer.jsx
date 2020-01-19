import React, { useState, useEffect } from 'react'
import Leaderboard from './Leaderboard'
import { withStyles } from '@material-ui/styles';

const styles = {
  leaderboardContainer: {
    width: "100%",
    height: "auto",
    display: "flex",
    justifyContent: "center"
  }
};

function LeaderboardContainer({ gamesObj, classes }) {

  const [gamesList, setGamesList] = useState([]);
  const [leaderboardList, setLeaderboardList] = useState([]);


  useEffect(() => {
    const extractGamesData = () => {
      setGamesList(gamesObj.games);
      setLeaderboardList(gamesObj.leaderboard);
    }
    extractGamesData()
  }, [gamesObj.games, gamesObj.leaderboard])

  return (
    <div className={classes.leaderboardContainer}>
      <Leaderboard leaderboardList={leaderboardList} />
    </div>
  );
}

export default withStyles(styles)(LeaderboardContainer);
