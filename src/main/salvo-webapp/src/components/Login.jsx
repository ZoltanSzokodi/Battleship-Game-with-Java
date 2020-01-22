import React, { useState } from 'react'
import axios from 'axios'
import { Link } from "react-router-dom";

function Login() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = e => {
    e.preventDefault()
    const bodyFormData = new FormData();
    bodyFormData.set('username', username);
    bodyFormData.set('password', password);

    axios({
      method: 'post',
      url: 'http://localhost:8080/api/login',
      data: bodyFormData,
      headers: { 'Content-Type': 'multipart/form-data' }
    })
      .then(function (response) {
        //handle success
        console.log(response);
      })
      .catch(function (response) {
        //handle error
        console.log(response);
      });
  };

  const handleLogout = e => {
    e.preventDefault()

    axios({
      method: 'post',
      url: 'http://localhost:8080/api/logout'
    })
      .then(function (response) {
        //handle success
        console.log(response);
      })
      .catch(function (response) {
        //handle error
        console.log(response);
      });
  };

  return (
    <div>
      <h1>LOGIN</h1>
      <div>
        <form onSubmit={handleLogin}>
          <input onChange={e => setUsername(e.target.value)} type="text" name="username" placeholder="Enter user name:" />
          <input onChange={e => setPassword(e.target.value)} type="text" name="password" placeholder="Enter password:" />
          <button type="submit">Log in</button>
        </form>
      </div >

      <form onSubmit={handleLogout} >
        <button type="submit">Log out</button>
      </form>
      <Link to="/games">GAMES</Link>
    </div>
  );
}

export default Login;
