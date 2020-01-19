import React, { useEffect, useState } from 'react'
import LeaderboardContainer from './LeaderboardContainer'
import axios from 'axios'
import { withStyles } from '@material-ui/styles';

const styles = {
  leaderboardWrapper: {
    width: "100vw",
    height: "100vh",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center"
  }
}

function Games({ classes }) {
  const [gamesObj, setGamesObj] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getGamesObj = async () => {
      const url = 'http://localhost:8080/api/games';

      try {
        let response = await axios.get(url)
        let data = await response.data;
        setGamesObj(data);
        setLoading(false);
      } catch (err) { console.log(err) }
    }
    getGamesObj();
  }, [])


  return (
    <div className={classes.leaderboardWrapper}>
      {!loading && <LeaderboardContainer gamesObj={gamesObj} />}
    </div>
  )
}

export default withStyles(styles)(Games);
