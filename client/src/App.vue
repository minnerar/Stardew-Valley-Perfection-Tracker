<template>
  <div id="stardew-app">
    <header>
      <nav id="navigation">
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          <button>LOGOUT</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else><button>LOGIN</button></router-link>
        <router-link v-bind:to="{ name: 'home'}"><button>HOME</button></router-link>
        <a href="https://stardewvalleywiki.com/Stardew_Valley_Wiki" class="button"><button>WIKI</button></a>
        <a href="https://www.nexusmods.com/stardewvalley" class="button"><button>MODS</button></a>
      </nav>
      <div class="header-content">
        <img id="home-image" src="src/assets/stardew-main-logo.png" alt="src/assets/main-image-alt.png"/>
        <h1 id="app-title">PERFECTION TRACKER</h1>
      </div>
    </header>

    <main>
      <router-view />
    </main>

    <footer>
      <div><img id="footer-img" src="src/assets/Developed_By_Image_small.gif" /></div>
      &copy; 2024 All Rights Reserved
    </footer>
  </div>
</template>

<style scoped>
/* General Styling */
#stardew-app {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

/* Header Styling */
header {
  background-color: lightgreen;
  display: flex;
  flex-direction: column;
}

/* Navigation Styling */
nav {
  display: flex;
  justify-content: flex-end;
  background-color: white;
  padding: 10px;
}

/* Header Content Styling */
.header-content {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
}

#home-image {
  height: 100px; 
  margin-right: 25px; 
}

#app-title {
  font-size: 2rem; 
  font-weight: bold; 
}

main {
  flex: 1; 
  display: flex;
  align-items: center; 
  justify-content: center; 
}

/* Footer Styling */
footer {
  background-color: lightgreen;
  text-align: center; 
  padding: 10px;
  display: flex;
  flex-direction: column; 
  align-items: center; 
}

/* Button Styling */
button {
  padding: 0.75rem 1.5rem;
  background-color: white;
  color: darkgreen;
  border: 1px solid transparent;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

button:active {
  box-shadow: inset 0px 0px 5px #c1c1c1;
  outline: none;
}

button:hover {
  background-color: lightgray;
}
</style>
