import React from 'react'

function Login() {
  return (
    <div>
      <form id="login-form" onsubmit="return false">
        <label>Name: <input type="text" name="username" /></label>
        <label>Password: <input type="text" name="password" /></label>
      </form>
    </div>

  );
}

export default Login;
