import React from 'react'
import '../styles/Cell.css'

function Cell({ prop }) {

  return (
    <div className="cell">
      <span>{prop}</span>
    </div>
  )
}

export default Cell;
