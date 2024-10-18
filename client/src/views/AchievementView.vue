<template>
  <div class="achievement-detail">
    <div class="achievement-info">
      <img :src="achievement.imageURL" />
      <h2 class="achievement-name">{{ achievement.achievementName }}</h2>
      <p>
        {{
          achievement.achievementProgress == 100 ? "Completed" : "Incomplete"
        }}
      </p>
      <button @click="toggleAchievementStatus">
        Mark as {{ toggleStatus ? "Incomplete" : "Complete" }}
      </button>
      <p>{{ achievement.achievementDescription }}</p>
      <p>
        <strong>Total Needed:</strong> {{ achievement.achievementTotalNeeded }}
      </p>
      <!-- <p><strong>Current:</strong> {{ achievement.achievementCurrent }}</p> -->
      <p><strong>Progress:</strong> {{ achievement.achievementProgress }}</p>
      <p>
        <strong>Update Progress:</strong>
        <button @click="decrement" :disabled="count === 0">-</button>
        <span>{{ achievement.achievementProgress }}</span>
        <button @click="increment" :disabled="count === achievement.achievementTotalNeeded">+</button>
      </p>
      <!-- need to add an input box here to set progress -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      toggleStatus: false,
      count: 0
    };
  },
  methods: {
    toggleAchievementStatus() {
      this.toggleStatus = !this.toggleStatus;
      if (this.toggleStatus) {
        this.achievement.achievementProgress = 100;
        this.achievement.achievementCurrent =
          this.achievement.achievementTotalNeeded;
      } else {
        this.achievement.achievementProgress = 0;
        this.achievement.achievementCurrent = 0;
      }
    },
    getImageUrl(name) {
      return new URL(name, import.meta.url).href;
    },
    setProgress(input) {
      this.achievement.achievementProgress == input;
    },
    increment() {
      if (this.count < this.achievement.achievementTotalNeeded) {
        this.count++;
        this.achievement.achievementProgress++;
      }
    },
    decrement() {
      if (this.count > 0) {
        this.count--;
        this.achievement.achievementProgress--;
      }
    }
  },
  computed: {
    achievement() {
      return this.$store.state.achievements.find((achievement) => {
        return achievement.achievementId == this.$route.params.achievementId;
      });
    },
  },
};
</script>

<style>

.achievement-detail {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: 'Arial', sans-serif;
}

.achievement-info {
  text-align: center;
}

.achievement-info img {
  max-width: 100%;
  height: auto; /* Maintain aspect ratio */
  border-radius: 8px;
  margin-bottom: 15px;
  display: block; 
  margin-left: auto;
  margin-right: auto;
}

.achievement-name {
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