import React, { useEffect, useState } from 'react'
import axios from 'axios'
import { uuid } from 'uuidv4'

function Games() {
  const [gamesList, setGamesList] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8080/api/games')
      .then(res => {
        setGamesList(res.data)
      })
      .catch(err => console.log(err))
  }, [])

  console.log(gamesList)

  return (
    <div>

    </div>
  )
}

export default Games
