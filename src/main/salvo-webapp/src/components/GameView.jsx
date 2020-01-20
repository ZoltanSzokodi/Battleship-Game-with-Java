import React, { useEffect, useState } from 'react'
import axios from 'axios'
import TablesContainer from './TablesContainer'
import { withStyles } from '@material-ui/styles';
import { getParameterByName } from '../helpers/functions'

const styles = {
  gameViewWrapper: {
    width: "100vw",
    height: "100vh",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center"
  },
  tablesContainer: {
    width: "100%",
    display: "flex",
    justifyContent: "space-evenly"
  }
}

function GameView({ classes }) {
  // extract parameters from the url and place it in a variable
  const [gameViewObj, setGameViewObj] = useState({});
  // const [isLoading, setIsLoading] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [isError, setIsError] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');

  useEffect(() => {
    // if I leave this changing variable outside of the useEffect I need to include it in the dependncy array
    const gamePlayerID = getParameterByName('gp');

    const fetchData = async () => {
      setIsError(false)
      //setIsLoading(true);

      try {
        let response = await axios.get(`http://localhost:8080/api/game_view/${gamePlayerID}`);
        //let data = await response.data;
        setGameViewObj(response.data)
        setIsLoading(false)
      } catch (error) {
        setIsError(true)
        setErrorMsg(error.message);
      }
      // setIsLoading(false)
    }
    fetchData()
  }, [])


  return (
    <div className={classes.gameViewWrapper}>
      {isError ?
        (<div>{errorMsg}</div>)
        : isLoading ?
          (<div>LOADING...</div>)
          : (<TablesContainer gameViewObj={gameViewObj} />)}
    </div>
  );
}

export default withStyles(styles)(GameView);
