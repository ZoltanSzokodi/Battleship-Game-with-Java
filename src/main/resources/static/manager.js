const input = document.getElementById("input");
const output = document.getElementById("output");
const submitBtn = document.getElementById("add_player");

const URL = "http://localhost:8080/players";

// display text in the output area
function showData(text) {
  return output.innerText = text;
}

// event handler for when user clicks add person 
function addPlayer() {
  let userEmail = input.value;
  if (userEmail) {
    postPlayer(userEmail, URL)
  }
}

// load and display JSON sent by server for /players  
async function loadData(url) {
  try {
    const resp = await fetch(url);
    const data = await resp.json();
    console.log(data);
    showData(JSON.stringify(data, null, 2));
  } catch (err) {
    console.log(err)
  }
}

// code to post a new player using AJAX
// on success, reload and display the updated data from the server 
async function postPlayer(email, url) {
  try {
    await fetch(url, {
      method: 'POST',
      body: JSON.stringify({ "userName": email }),
      headers: new Headers({
        'Content-Type': 'application/json'
      }),
      dataType: "text"
    })
    showData("saved - RELOADING");
    loadData(url);
  } catch (err) {
    console.log(err)
  }
}

// add event listener
submitBtn.addEventListener("click", addPlayer)

loadData(URL)







