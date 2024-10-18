<template>
  <div class="villagers">
    <h2>VILLAGERS</h2>
    <ul id="villager-list">
      <li v-for="villager in villagers"
        v-bind:key="villager.villagerId"
        @click="villagerView(villager.villagerId)">
        <div class="villager-list-item">
          <div>{{ villager.villagerName }}</div>
          <!-- <img :src="villager.imageURL" /> -->
          <span class="icon-container" v-if="isAdmin">
            <router-link
              v-bind:to="{
                name: 'villager',
                params: { villagerId: villager.villagerId },
              }"
            >
              ADD
            </router-link>
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  computed: {
    villagers() {
      return this.$store.state.villagers;
    },
    isAdmin() {
      return (
        this.$store.state.user &&
        this.$store.state.user.authoritiesString &&
        this.$store.state.user.authoritiesString.includes("ROLE_ADMIN")
      );
    },
  },
  methods: {
    villagerView(id) {
        this.$router.push({name: "villager", params: {villagerId: id}});
    }
  }
};
</script>

<style scoped>

.villagers {
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

#villager-list {
  list-style-type: none; 
  padding: 0;
  overflow-y: auto; 
  flex: 1; 
}

#villager-list li {
  cursor: pointer; 
  padding: 10px;
  margin: 10px 0;
  border-radius: 4px; 
  transition: background-color 0.3s ease; 
  background-color: #fafafa;
}

#villager-list li:hover {
  background-color: #e0f7fa; 
}

.villager-list-item {
  display: flex;
  justify-content: space-between; 
  align-items: center;
}

.villager-name {
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