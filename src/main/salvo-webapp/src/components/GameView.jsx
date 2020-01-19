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
  const gamePlayerID = getParameterByName('gp');

  const [gameViewObj, setGameViewObj] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const getGameViewObj = async () => {
      const url = `http://localhost:8080/api/game_view/${gamePlayerID}`;

      try {
        let response = await axios.get(url);
        let data = await response.data;
        setGameViewObj(data);
        setLoading(false)
      } catch (err) { console.log(err) }
    }
    getGameViewObj()
  }, [gamePlayerID])


  return (
    <div className={classes.gameViewWrapper}>
      {!loading && <TablesContainer gameViewObj={gameViewObj} />}
    </div>
  )
}

export default withStyles(styles)(GameView);
