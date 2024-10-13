<template>
  <div id="stardew-app">
    <header><img src="src\assets\stardew-main-logo.png" alt="src\assets\main-image-alt.png"/>
      <h1>PERFECTION TRACKER</h1>
    <nav>
        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          LOGOUT
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>LOGIN</router-link>
        <router-link v-bind:to="{ name: 'home'}"><button>HOME</button></router-link>
        <a href="https://stardewvalleywiki.com/Stardew_Valley_Wiki" class="button">WIKI</a>
        <a href="https://www.nexusmods.com/stardewvalley" class="button">MODS</a>
    </nav>
  </header>
  
    <main>
      <router-view />
    </main>

    <footer>
      <div><img src="src\assets\Developed_By_Image_small.gif" /></div>
      &copy; 2024 All Rights Reserved</footer>
  </div>
</template>

<style scoped>

</style>