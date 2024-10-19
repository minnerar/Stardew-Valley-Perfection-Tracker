<template>
  <div>
    {{ currentTotalProgress() }}
  </div>
  <div id="main-div">
    <div id="achievement-container">
      <achievement-list />
    </div>
    <div id="item-container">
      <item-list />
    </div>
    <div id="villager-container">
      <villager-list />
    </div>
  </div>
</template>


<script>
// imports for the home view
import { resourceService } from "../services/ResourceService.js";
import AchievementList from "../components/AchievementList.vue";
import ItemList from "../components/ItemList.vue";
import VillagerList from "../components/VillagerList.vue";

export default {
  components: { AchievementList, ItemList, VillagerList },
  data() {
    return {
      isLoading: false,
    };
  },
  created() {
    this.isLoading = true;
    Promise.all([
      resourceService.getAchievements(),
      resourceService.getItems(),
      resourceService.getVillagers(),
      resourceService.getClassifications(),
    ]).then(
      ([
        achievementResponse,
        itemResponse,
        villagerResponse,
        classificationResponse,
      ]) => {
        this.$store.commit("SET_ACHIEVEMENTS", achievementResponse.data);
        this.$store.commit("SET_ITEMS", itemResponse.data);
        this.$store.commit("SET_VILLAGERS", villagerResponse.data);
        this.$store.commit("SET_CLASSIFICATIONS", classificationResponse.data);
        this.isLoading = false;
      }
    );
  },
  methods: {
    currentTotalProgress() {
      // calculates the current progress towards perfection (only including achievements)
      let currentCompleted = this.$store.state.achievements.find(
        (achievement) => {
          return achievement.achievementProgress == 100;
        }
      );
      // return currentCompleted.length / resourceService.achievements.length;
    },
  },
};
</script>

<style>
#main-div {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px; 
}

#achievement-list {
  grid-area: achievement;
}

#item-list {
  grid-area: items;
}

nav {
  background-color: #6c757d;
  padding: 10px;
}

nav ul {
  list-style: none;
  display: flex;
  justify-content: space-around;
  padding: 0;
  margin: 0;
}

nav a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}

.header {
  text-align: center;
  margin: 20px 0;
}

.header-image {
  max-width: 100%;
  height: auto;
  border-radius: 10px;
}

.header-title {
  font-size: 3em; 
  color: #333;
  margin: 20px 0;
}

#main-div {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px; 
}

#achievement-container,
#item-container,
#villager-container {
  background-color: #ffffff; 
  padding: 20px;
  border-radius: 8px; 
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); 
  max-height: 400px; 
  overflow-y: auto; 
}

h2 {
  text-align: center; 
  color: #2e8b57; 
  margin-bottom: 20px;
}

.footer {
  text-align: center;
  margin-top: 20px;
  padding: 10px 0;
  background-color: #f9f9f9;
}
</style>