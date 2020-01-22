import React, { useState } from 'react'
import axios from 'axios'

function Login() {

  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')

  const handleSubmit = e => {
    e.preventDefault()

    axios.post('http://localhost:8080/api/login', {
      username: username,
      password: password
    })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  console.log(username)


  return (
    <div>
      <h1>LOGIN</h1>
      <div>
        <form onSubmit={handleSubmit}>
          <input onChange={e => setUsername(e.target.value)} type="text" name="username" placeholder="Enter user name:" />
          <input onChange={e => setPassword(e.target.value)} type="text" name="password" placeholder="Enter password:" />
          <button type="submit">Submit</button>
        </form>
      </div >

      <form id="logout-form" >
        <button>Log out</button>
      </form>
    </div>
  );
}

export default Login;
