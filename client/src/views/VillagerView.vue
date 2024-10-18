<template>
  <div class="villager-detail">
    <div class="villager-info">
      <img :src="selectedVillager.imageURL" />
      <h2 class="villager-name">{{ selectedVillager.villagerName }}</h2>
      <p>{{ selectedVillager.villagerDescription }}</p>
      <p>
        <strong>Current Hearts:</strong>
        <button @click="decrement" :disabled="count === 0">-</button>
        <span>{{ count }}</span>
        <button @click="increment" :disabled="count === 8">+</button>
      </p>
      <p>
        <strong>Marriage Candidate:</strong>
        {{ selectedVillager.villagerMarriageCandidate }}
      </p>
      <p><strong>Birthday:</strong> {{ selectedVillager.villagerBirthday }}</p>
      <p><strong>Loved Gifts:</strong> {{ removeNull(selectedVillager.villagerLovedGifts) }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      count: 0
    }
  },
  props: {
    villager: Object,
  },
  methods: {
    increment() {
      if (this.count < 10) {
        this.count++;
      }
    },
    decrement() {
      if (this.count > 0) {
        this.count--;
      }
    },
    removeNull(giftArray) {
      return giftArray.filter( gift => {
        return gift != null;
      });
    }
  },
  computed: {
    selectedVillager() {
      return this.$store.state.villagers.find( villager => {
        return villager.villagerId == this.$route.params.villagerId;
      });
    }
  }
};
</script>

<style>

.villager-detail {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.villager-info {
  text-align: center;
}

.villager-info img {
  max-width: 100%;
  height: auto; 
  border-radius: 8px;
  margin-bottom: 15px;
  display: block; 
  margin-left: auto;
  margin-right: auto; 
}

.villager-name {
  font-size: 1.8em;
  color: #333;
  margin-bottom: 10px;
}

p {
  color: #555;
  line-height: 1.6;
  margin: 5px 0;
}

strong {
  color: #333;
}

button {
  background-color: #6c757d;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  font-size: 1em;
  margin: 0 5px;
  transition: background-color 0.3s;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

button:hover:not(:disabled) {
  background-color: #5a6268;
}

span {
  font-weight: bold;
  margin: 0 10px;
}

</style>