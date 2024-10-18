<template>
  <div id="register" class="register-container">
    <form v-on:submit.prevent="register" class="register-form">
      <h1>Create Account</h1>
      <div id="fields">
        <div class="field-group">
          <label for="username">Username</label>
          <input
            type="text"
            id="username"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div class="field-group">
          <label for="name">Name</label>
          <input
            type="text"
            id="name"
            placeholder="Name"
            v-model="user.name"
            required
          />
        </div>
        <div class="field-group">
          <label for="password">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            v-model="user.password"
            required
          />
        </div>
        <div class="field-group">
          <label for="confirmPassword">Confirm Password</label>
          <input
            type="password"
            id="confirmPassword"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
        </div>
        <div class="field-group">
          <label for="address">Address</label>
          <input
            type="text"
            id="address"
            placeholder="Address"
            v-model="user.address"
          />
        </div>
        <div class="field-group">
          <label for="city">City</label>
          <input
            type="text"
            id="city"
            placeholder="City"
            v-model="user.city"
          />
        </div>
        <div class="field-group">
          <label for="state">State</label>
          <input
            type="text"
            id="state"
            placeholder="State"
            v-model="user.stateCode"
            maxlength="2"
            required
          />
        </div>
        <div class="field-group">
          <label for="zip">ZIP</label>
          <input
            type="number"
            id="zip"
            placeholder="ZIP"
            v-model="user.zip"
            required
            minlength="5"
            maxlength="5"
          />
        </div>
        <div>
          <button type="submit">Create Account</button>
        </div>
      </div>
      <hr />
      Have an account?
      <router-link v-bind:to="{ name: 'login' }">Sign in!</router-link>
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
        name: "",
        password: "",
        confirmPassword: "",
        address: "",
        city: "",
        stateCode: "",
        zip: "",
        role: "user",
      },
    };
  },
  methods: {
    error(msg) {
      alert(msg);
    },
    success(msg) {
      alert(msg);
    },
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.error("Password & Confirm Password do not match");
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.success("Thank you for registering, please sign in.");
              this.$router.push({
                path: "/login",
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            if (!response) {
              this.error(error);
            } else if (response.status === 400) {
              if (response.data.errors) {
                let msg = "Validation error: ";
                for (let err of response.data.errors) {
                  msg += `'${err.field}':${err.defaultMessage}. `;
                }
                this.error(msg);
              } else {
                this.error(response.data.message);
              }
            } else {
              this.error(response.data.message);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.register-form {
  display: flex;
  flex-direction: column;
  gap: 15px; /* Spacing between fields */
}

h1 {
  font-size: 2em;
  color: #333;
  text-align: center;
}

.field-group {
  margin-bottom: 15px; /* Space between label and input box */
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
