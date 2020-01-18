import React, { useState, useEffect } from 'react'
import Table from './Table'
import { withStyles } from '@material-ui/styles';

const styles = {
  tablesContainer: {
    width: "100%",
    display: "flex",
    justifyContent: "space-evenly"
  }
}

function TablesContainer({ gameViewObj, classes }) {

  const colsArr = [];
  const rowsArr = [];
  // column heads
  for (let i = 0; i <= 10; i++) { i === 0 ? colsArr.push("") : colsArr.push(i) }
  // row heads
  for (let i = 65; i <= 74; i++) { rowsArr.push(String.fromCharCode(i)) }

  const {
    ships,
    salvos,
    //gamePlayer_ID,
    playerName,
    opponentInfo
  } = gameViewObj;

  const [playerShips, setPlayerShips] = useState([]);
  const [playerSalvos, setPlayerSalvos] = useState([]);
  const [opponentSalvos, setOpponentSalvos] = useState([]);

  useEffect(() => {
    const extractGameViewData = () => {
      let playerShipsArr = [];
      let playerSalvosArr = [];
      let opponentSalvosArr = [];

      ships.forEach(ship => (
        playerShipsArr.push(...ship.location)
      ))
      salvos.forEach(salvo => (
        playerSalvosArr.push(...salvo.location)
      ))

      opponentInfo.opponentSalvos.forEach(salvo => (
        opponentSalvosArr.push(...salvo.location)
      ))
      setPlayerShips(playerShipsArr)
      setPlayerSalvos(playerSalvosArr)
      setOpponentSalvos(opponentSalvosArr)
    }
    extractGameViewData()
  }, [ships, salvos, opponentInfo.opponentSalvos])

  function toggleCellClass(
    typeOfTable,
    hitClass,
    missClass,
    shipLocationClass,
    salvoLocationClass,
    emptyCellClass,
    idx) {

    if (typeOfTable === "ship") {
      if (playerShips.includes(idx)) {
        // show player ship's location grid which has been hit by opponent
        return opponentSalvos.includes(idx) ? hitClass : shipLocationClass
      }
      else if (!playerShips.includes(idx)) {
        // show missed shots of opponent on player's table
        return opponentSalvos.includes(idx) ? missClass : emptyCellClass
      }
    }
    else if (typeOfTable === "salvo") {
      // show player's salvos on opponent's table
      return playerSalvos.includes(idx) ? salvoLocationClass : emptyCellClass
    }
  }

  console.log(playerSalvos)

  return (
    <React.Fragment>
      <h2>Player: {playerName} Opponent: {opponentInfo.opponentName}</h2>

      <div className={classes.tablesContainer}>

        <Table
          tableType="ship"
          colsArr={colsArr}
          rowsArr={rowsArr}
          toggleCellClass={toggleCellClass}
        />

        <Table
          tableType="salvo"
          colsArr={colsArr}
          rowsArr={rowsArr}
          toggleCellClass={toggleCellClass}
        />
      </div>
    </React.Fragment>
  )
}

export default withStyles(styles)(TablesContainer);
