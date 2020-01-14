import React from 'react'
import Games from './components/Games'
import GameTable from './components/GameTable'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import './styles/App.css';

function App() {
  return (
    <Router>
      <React.Fragment>
        <Route exact path="/games" component={Games} />
        <Route exact path="/game" component={GameTable} />
      </React.Fragment>
    </Router>
  )
}

export default App;
