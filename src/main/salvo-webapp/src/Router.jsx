import React from 'react'
import Games from './components/Games'
import GameTable from './components/GameView'
import { BrowserRouter, Route } from 'react-router-dom'

function Router() {
  return (
    <BrowserRouter>
      <React.Fragment>
        <Route exact path="/games" component={Games} />
        <Route exact path="/game" component={GameTable} />
      </React.Fragment>
    </BrowserRouter>
  )
}

export default Router;
