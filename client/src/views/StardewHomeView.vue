<template>
  <!-- <div class="progress-bar" role="progressbar" v-bind:aria-valuenow="currentCompletedAchievements/totalAchievements" aria-valuemin="0" aria-valuemax="totalAchievements" style="width:70%">
  {{this.score}}
  </div> -->
  <!-- <div> -->
  <!-- {{ currentCompletedAchievements / totalAchievements }} -->
  <!-- </div> -->
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
import { Store } from "vuex";

export default {
  components: { AchievementList, ItemList, VillagerList },
  data() {
    return {
      isLoading: false,
      totalCompleted: 0,
      currentCompletedAchievements: 0,
      totalAchievements: 0,
      score: 0,
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
    this.totalAchievements = this.$store.state.achievements.length;
    this.currentCompletedAchievements = this.$store.state.achievements.find(
      (achievement) => {
        return (achievement.achievementCurrent = 1);
      }
    );
  },
  methods: {
    currentTotalProgress() {
      // calculates the current progress towards perfection (only including achievements)
      // return this.currentCompletedAchievements / this.totalAchievements;
    },
  },
  computed: {
    isAdmin() {
      return (
        // checks if the user is an admin
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
  },
};
</script>

<style>
html {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
}

#main-div {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 20px;
  height: auto;
  overflow-y: auto;
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

#achievement-container,
#item-container,
#villager-container {
  background-color: #ffffff;
  padding: 20px;
  border-radius: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  max-height: 500px;
  overflow-y: auto;
  margin: 25px;
}

h2 {
  text-align: center;
  color: #2e8b57;
  margin-bottom: 20px;
}

.footer {
  text-align: center;
  margin-top: 20px;
  padding: 10px 10px;
  background-color: #f9f9f9;
  flex-direction: column;
}

/* Mobile View */
@media (max-width: 425px) {
  #main-div {
    grid-template-columns: 1fr;
    gap: 10px;
  }

  .header-title {
    font-size: 1em;
  }

  nav ul {
    flex-direction: column;
    align-items: center;
  }

  nav a {
    padding: 5px 0;
  }

  #achievement-container,
  #item-container,
  #villager-container {
    max-height: 400px;
    padding: 15px;
    margin: 0px;
  }

  .footer {
    text-align: center;
    margin-top: 10px;
    padding: 10px 0;
    background-color: #f9f9f9;
    flex-direction: row;
  }
}
</style>
