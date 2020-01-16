import React, { useEffect, useState } from 'react'
import axios from 'axios'
import Table from './Table'
import { withStyles } from '@material-ui/styles';

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
  function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    const regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
    let results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
  }

  const gamePlayerID = getParameterByName('gp');

  const [gameViewObj, setGameViewObj] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/game_view/${gamePlayerID}`)
      .then(res => {
        setGameViewObj(res.data)
        setLoading(loading => !loading)
      })
      .catch(err => console.log(err))
  }, [gamePlayerID])

  console.log(gameViewObj)

  return (
    <div className={classes.gameViewWrapper}>
      {!loading && <h2>Player: {gameViewObj.player_name} Opponent: {gameViewObj.opponent_info.opponent_name}</h2>}
      <div className={classes.tablesContainer}>
        {!loading && <Table tableType="ship" gameViewObj={gameViewObj} />}
        {!loading && <Table tableType="salvo" gameViewObj={gameViewObj} />}

      </div>
    </div>
  )
}

export default withStyles(styles)(GameView);
