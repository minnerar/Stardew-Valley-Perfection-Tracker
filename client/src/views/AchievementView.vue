<template>
  <div class="achievement-detail">
    <div class="achievement-info">
      <image>{{ achievement.imageURL }}</image>
      <h2 class="item-name">{{ achievement.name }}</h2>
      <p>{{ achievement.completed ? 'Completed' : 'Incomplete'}}</p>
      <button @click="toggleStatus">Mark as Complete or Incomplete</button>
      <!-- work on this part. want the button to toggle back and forth from complete to incomplete -->
      <p>{{ achievement.description }}</p>
      <p><strong>Total Needed:</strong> {{ achievement.totalNeeded }}</p>
      <p><strong>Current:</strong> {{ achievement.current }}</p>
      <p><strong>Progress:</strong> {{ item.progress }}</p>
    </div>
  </div>
</template>

<script>
export default {
  methods: {

  },
  computed: {
    achievement() {
      return this.$store.state.achievement.find( achievement => {
        return achievement.achievementId == this.$route.params.achievementId;
      });
    }
  }
}
</script>

<style>

</style>