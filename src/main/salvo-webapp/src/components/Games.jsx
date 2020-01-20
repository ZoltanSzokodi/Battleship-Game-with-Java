import React, { useEffect, useState } from 'react'
import LeaderboardContainer from './LeaderboardContainer'
import axios from 'axios'
import { withStyles } from '@material-ui/styles';

const styles = {
  leaderboardWrapper: {
    width: "100vw",
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center"
  }
};

function Games({ classes }) {
  const [gamesObj, setGamesObj] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isError, setIsError] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      setIsError(false)
      setIsLoading(true);

      try {
        let response = await axios.get('http://localhost:8080/api/games')
        setGamesObj(response.data);
        setIsLoading(false);
      } catch (error) {
        setIsError(true)
        setErrorMsg(error.message);
      }
      setIsLoading(false)
    }
    fetchData();
  }, [])


  return (
    <div className={classes.leaderboardWrapper}>
      {isError ?
        (<div>{errorMsg}</div>)
        : isLoading ?
          (<div>LOADING...</div>)
          : (<LeaderboardContainer gamesObj={gamesObj} />)}
    </div>
  );
}

export default withStyles(styles)(Games);
