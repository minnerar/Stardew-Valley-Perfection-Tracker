<template>
  <section id="items">
    <h2>ITEMS</h2>
    <ul id="item-list">
      <li
        v-for="item in items"
        v-bind:key="item.itemId"
        @click="itemView(item.itemId)"
      >
        <div class="item-list-item">
          <div>{{ item.itemName }}</div>
          <!-- <img :src="item.imageURL" /> -->
        </div>
      </li>
    </ul>
  </section>
</template>

<script>
export default {
  computed: {
    items() {
      return this.$store.state.items;
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
    itemView(id) {
      // push to the individual item page view
      this.$router.push({name: "item", params: {itemId: id}});
    },
    // isAdmin() {
    //   return (
    //     this.$store.state.user &&
    //     this.$store.state.user.role.includes("ROLE_ADMIN")
    //   );  
    // },
  }
}
</script>

<style scoped>
#items {
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

#item-list {
  list-style-type: none;
  padding: 0;
  overflow-y: auto;
  flex: 1;
}

#item-list li {
  cursor: pointer;
  padding: 10px;
  margin: 10px 0;
  border-radius: 4px;
  transition: background-color 0.3s ease;
  background-color: #fafafa;
}

#item-list li:hover {
  background-color: #e0f7fa;
}

.item-list-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.item-name {
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