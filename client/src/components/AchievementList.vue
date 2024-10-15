<template>
  <section id="achievements">
    <h2>ACHIEVEMENTS</h2>
    <ul id="achievement-list">
      <li v-for="achievement in achievements" 
          v-bind:key="achievement.achievementId"
          @click="achievementView(achievement.achievementId)">
          <div class="achievement-list-item">
            <div>{{ achievement.achievementName }}</div>
            <span class="icon-container" v-if="isAdmin">
              <router-link v-bind:to="{
                name: 'addAchievement', 
                params: { achievementId: achievement.achievementId }}">ADD</router-link>
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
    isAdmin() {
      return this.$store.state.user && 
        this.$store.state.user.authoritiesString &&
        this.$store.state.user.authoritiesString.includes("ROLE_ADMIN");
    }
  },
  methods: {
    achievementView(id) {
      this.$router.push("/achievement");
    }
  }

}
</script>

<style>

</style>