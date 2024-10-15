<template>
  <div id="main-div">
    <AchievementList id="achievement-list" />
    <ItemList id="item-list" />
  </div>
</template>

<script>
// imports for the home view
import { resourceService } from "../services/ResourceService.js";
import AchievementList from "../components/AchievementList.vue";
import ItemList from "../components/ItemList.vue";

export default {
  components: { AchievementList, ItemList },
  data() {
    return {
      isLoading: false,
    };
  },
  created() {
    this.isLoading = true;
    Promise.all([
      resourceService.getAchievements()
    //   resourceService.getItems(),
    //   resourceService.getVillagers(),
    //   resourceService.getClassifications()
        .then(
          (
            achievementResponse,
            // itemResponse,
            // villagerResponse,
            // classificationResponse
          ) => {
            this.$store.commit("SET_ACHIEVEMENTS", achievementResponse.data);
            // this.$store.commit("SET_ITEMS", itemResponse.data);
            // this.$store.commit("SET_VILLAGERS", villagerResponse.data);
            // this.$store.commit("SET_CLASSIFICATIONS", classificationResponse.data);
            this.isLoading = false;
          }
        ),
    ]);
    resourceService.getItems().then( (response) => {this.$store.commit("SET_ITEMS"), response.data});
    resourceService.getVillagers().then( (response) => {this.$store.commit("SET_VILLAGERS"), response.data});
    resourceService.getClassifications().then( (response) => {this.$store.commit("SET_CLASSIFICATIONS"), response.data});
  },
  methods: {
    // getItems() {
    //     resourceService.getItems().then( (response) => {this.$store.commit("SET_ITEMS"), response.data});
    // },
    // getVillagers() {
    //     resourceService.getVillagers().then( (response) => {this.$store.commit("SET_VILLAGERS"), response.data});
    // },
    // getClassifications() {
    //     resourceService.getClassifications().then( (response) => {this.$store.commit("SET_CLASSIFICATIONS"), response.data});
    // }
  }
};
</script>

<style>
#main-div {
  grid-template-columns: 1fr 1fr;
  grid-template-areas: "achievement items";
  align-content: center;
}

#achievement-list {
    grid-area: achievement;
}

#item-list {
    grid-area: items;
}
</style>