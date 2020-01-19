import React, { useState, useEffect } from 'react'
import Leaderboard from './Leaderboard'

function LeaderboardContainer({ gamesObj }) {

  const [gamesList, setGamesList] = useState([]);
  const [leaderboardList, setLeaderboardList] = useState([]);

  useEffect(() => {
    const extractGamesData = () => {
      setGamesList(gamesObj.games);
      setLeaderboardList(gamesObj.leaderboard);
    }
    extractGamesData()
  }, [gamesObj.games, gamesObj.leaderboard])

  // console.log(gamesList)
  // console.log(leaderboardList)

  return (
    <div>
      <Leaderboard leaderboardList={leaderboardList} />
    </div>
  )
}

export default LeaderboardContainer;
