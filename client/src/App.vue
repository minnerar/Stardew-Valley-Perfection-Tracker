<template>
  <div id="stardew-app">
    <header>
      <img id="home-image" src="src\assets\stardew-main-logo.png" alt="src\assets\main-image-alt.png"/>
      <h1 id="app-title">PERFECTION TRACKER</h1>
      <nav id="navigation">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          <button>LOGOUT</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else><button>LOGIN</button></router-link>
        <router-link v-bind:to="{ name: 'home'}"><button>HOME</button></router-link>
        <a href="https://stardewvalleywiki.com/Stardew_Valley_Wiki" class="button"><button>WIKI</button></a>
        <a href="https://www.nexusmods.com/stardewvalley" class="button"><button>MODS</button></a>
    </nav>
  </header>
  
    <main>
      <router-view />
    </main>

    <footer>
      <div><img id="footer-img" src="src\assets\Developed_By_Image_small.gif" /></div>
      &copy; 2024 All Rights Reserved</footer>
  </div>
</template>

<style scoped>
/* General Styling */
#stardew-app {
  height: 96vh;

  display: grid;

  grid-template-areas:
    "head"
    "main"
    "foot";

  gap: 5px;
}

/* Header Styling */

header {
  grid-area: head;
  background-color: lightgreen;
  align-items: center;
  display: flex;
}

/* Navigation Styling */

nav {
  flex-grow: 1;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: right;
  background-color: white;
}

button {
  padding: 0.75rem 1.5rem;
  background-color: white;
  color: darkgreen;
  border: 1px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

button:active {
  -webkit-box-shadow: inset 0px 0px 5px #c1c1c1;
  -moz-box-shadow: inset 0px 0px 5px #c1c1c1;
  box-shadow: inset 0px 0px 5px #c1c1c1;
  outline: none;
}

button:hover {
  background-color: lightgray;
}

.button-container {
  display: flex;
  justify-content: center;
  gap: 10px;
  margin-top: 20px;
}

/* Main Styling */

main {
  grid-area: main;
}

/* Footer Styling */
footer {
  grid-area: footer;
  background-color: lightgreen;
  align-content: center;
}

/* Form Styling */
form {
  max-width: 500px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
}

form label {
  font-size: 1rem;
  color: #333;
  margin-bottom: 0.5rem;
  display: block;
}

form input[type="text"],
form input[type="password"],
form input[type="number"],
form select,
form textarea {
  width: 100%;
  padding: 0.75rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  background-color: #fff;
  transition: border-color 0.3s ease;
  margin-bottom: 1rem;
}

form input[type="text"]:focus,
form input[type="password"]:focus,
form input[type="number"]:focus,
form select:focus,
form textarea:focus {
  border-color: lightsteelblue;
  outline: none;
}

form textarea {
  resize: vertical;
  height: 120px;
}

form div {
  margin-bottom: 1.5rem;
}
</style>