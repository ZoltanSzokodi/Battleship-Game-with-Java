import React from "react"
import Games from './components/Games'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import './App.css';

function App() {
  return (
    <Router>
      <React.Fragment>
        <Route exact path="/web/games" component={Games} />
      </React.Fragment>
    </Router>
  )
}

export default App;
