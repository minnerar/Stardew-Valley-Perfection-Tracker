<template>
  <div class="item-detail">
    <div class="item-info">
      <img :src="item.imageURL" />
      <h2 class="item-name">{{ item.itemName }}</h2>
      <p>{{ toggleStatus == true ? 'Completed' : 'Incomplete'}}</p>
      <button @click="toggleItemStatus">Mark as {{ toggleStatus ? "Incomplete" : "Complete" }}</button>
      <p>{{ item.description }}</p>
      <p><strong>Achievement:</strong> {{ item.achievement }}</p>
      <p><strong>Season:</strong> {{ item.itemSeason ? item.itemSeason : 'N/A' }}</p>
      <p><strong>Time:</strong> {{ item.itemTime ? item.itemTime : 'N/A' }}</p>
      <p><strong>Weather:</strong> {{ item.itemWeather ? item.itemWeather : 'N/A' }}</p>
      <p><strong>Location:</strong> {{ item.itemLocation ? item.itemLocation : 'N/A' }}</p>
      <p><strong>Classification:</strong> {{ item.classification ? item.classification : 'N/A' }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      toggleStatus: false
    };
  },
  methods: {
    toggleItemStatus() {
      this.toggleStatus = !this.toggleStatus;     
    },
    getItemById(id) {
      const item = this.$store.state.items.find( (item) => {
        return item.id = id;
      });
      this.$router.push('/item');
    },
  },
  computed: {
    item() {
      return this.$store.state.items.find( item => {
        return item.itemId == this.$route.params.itemId;
      });
    }

  }

}
</script>

<style>

.item-detail {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.item-info {
  text-align: center;
}

.item-info img {
  max-width: 100%;
  height: auto; 
  border-radius: 8px;
  margin-bottom: 15px;
  display: block; 
  margin-left: auto; 
  margin-right: auto; 
}

.item-name {
  font-size: 1.8em;
  color: #333;
  margin-bottom: 10px;
}

p {
  color: #555;
  line-height: 1.6;
  margin: 5px 0;
}

button {
  background-color: #6c757d;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 10px 15px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #5a6268;
}

strong {
  color: #333;
}

</style>