<template>
  <div class="item-detail">
    <div class="item-info">
      <image>{{ image.imageURL }}</image>
      <h2 class="item-name">{{ item.name }}</h2>
      <p>{{ item.completed ? 'Completed' : 'Incomplete'}}</p>
      <button @click="toggleStatus">Mark as Complete or Incomplete</button>
      <!-- work on this part. want the button to toggle back and forth from complete to incomplete -->
      <p>{{ item.description }}</p>
      <p><strong>Season:</strong> {{ item.season }}</p>
      <p><strong>Time:</strong> {{ item.time }}</p>
      <p><strong>Weather:</strong> {{ item.weather }}</p>
      <p><strong>Location:</strong> {{ item.location }}</p>
      <p><strong>Classification:</strong> {{ item.classification }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      
    }
  },
  methods: {
    toggleStatus() {
      
    },
    getItemById(id) {
      const item = this.$store.state.items.find( (item) => {
        return item.id = id;
      });
      this.$router.push('/item');
    }
  }

}
</script>

<style>

</style>