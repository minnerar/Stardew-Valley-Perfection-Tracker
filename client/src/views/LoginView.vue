<template>
  <div id="login" class="login-container">
    <form v-on:submit.prevent="login" class="login-form">
      <h1>Please Sign In</h1>
      <div id="fields">
        <div>
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        /></div>
        <div>
        <label for="password">Password</label>
        <input
          type="password"
          id="password"
          placeholder="Password"
          v-model="user.password"
          required
        /></div>
        <div><button type="submit">Sign in</button></div>
      </div>
      <hr />
      Need an account? <router-link v-bind:to="{ name: 'register' }">Register!</router-link>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 15px; /* Spacing between fields */
}

h1 {
  font-size: 2em;
  color: #333;
  text-align: center;
}

label {
  margin-bottom: 5px;
  color: #555;
}

input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1em;
}

input:focus {
  border-color: #6c757d;
  outline: none;
}

button {
  background-color: #6c757d;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #5a6268;
}

hr {
  margin: 20px 0;
}

.router-link {
  text-align: center;
  display: block;
  margin-top: 10px;
  color: #6c757d;
  text-decoration: none;
}

.router-link:hover {
  text-decoration: underline;
}
</style>
