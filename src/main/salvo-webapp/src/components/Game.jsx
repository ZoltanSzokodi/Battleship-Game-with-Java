import React from 'react'
import Cell from './Cell'
import '../styles/Game.css'

function Game() {

  const colsArr = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const rowsArr = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"];

  function createTable() {
    for (let i = 0; i < colsArr.length; i++) {
      console.log(colsArr[i])
      for (let y = 0; y < rowsArr.length; y++) {
        if (colsArr[i] === 0) {
          console.log(rowsArr[y])
        } else {
          console.log(`${rowsArr[y]}${i}`)
        }
      }
    }
  }

  createTable()

  return (
    <div className="game-wrapper">
      <h1>Ships Location</h1>
      <div className="table">
        <Cell />
      </div>
    </div>
  )
}

export default Game;
