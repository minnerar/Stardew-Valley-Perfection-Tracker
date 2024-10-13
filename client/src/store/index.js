import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      achievements: [],
      items: [],
      villagers: [],
      classifications: [],
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_ACHIEVEMENTS(state, achievements) {
        state.achievements = achievements;
      },
      SET_ITEMS(state, items) {
        state.items = items;
      },
      SET_VILLAGERS(state, villagers) {
        state.villagers = villagers;
      },
      SET_CLASSIFICATIONS(state, classifications) {
        state.classifications = classifications;
      }
    },

  })
  return store;
}