<template>
  <section id="achievements">
    <h2>ACHIEVEMENTS</h2>
    <ul id="achievement-list">
      <li
        v-for="achievement in achievements"
        v-bind:key="achievement.achievementId"
        @click="achievementView(achievement.achievementId)"
      >
        <div class="achievement-list-item">
          <div>{{ achievement.achievementName }}</div>
          <!-- <img :src="achievement.imageURL" /> -->
          <span class="icon-container" v-if="isAdmin">
            <router-link
              v-bind:to="{
                name: 'editAchievement',
                params: { achievementId: achievement.achievementId },
              }"
              >ADD</router-link
            >
          </span>
        </div>
      </li>
    </ul>
  </section>
</template>

<script>
export default {
  computed: {
    achievements() {
      return this.$store.state.achievements;
    },
    // isAdmin() {
    //   return (
    //     this.$store.state.user &&
    //     this.$store.state.user.role.includes("ROLE_ADMIN")
    //   );
    // },
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.authoritiesString &&
        this.$store.state.user.authoritiesString.includes("ROLE_ADMIN")
      );
    },
  },
  methods: {
    achievementView(id) {
      this.$router.push({
        name: "achievement",
        params: { achievementId: id },
      });
    },
  },
};
</script>

<style scoped>
#achievements {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
}

h2 {
  text-align: center;
  color: #2e8b57;
  margin-bottom: 20px;
}

#achievement-list {
  list-style-type: none;
  padding: 0;
  overflow-y: auto;
  flex: 1;
}

#achievement-list li {
  cursor: pointer;
  padding: 10px;
  margin: 10px 0;
  border-radius: 4px;
  transition: background-color 0.3s ease;
  background-color: #fafafa;
}

#achievement-list li:hover {
  background-color: #e0f7fa;
}

.achievement-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.achievement-name {
  font-size: 1.2rem;
  color: #333;
}

.icon-container {
  margin-left: auto; /* Push ADD button to the right */
}

.icon-container router-link {
  text-decoration: none;
  color: #2e8b57;
  font-weight: bold;
  background-color: #e0f7fa;
  padding: 5px 10px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.icon-container router-link:hover {
  background-color: #b2ebf2;
}
</style>