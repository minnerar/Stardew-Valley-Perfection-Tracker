<template>
  <section id="items">
    <h2>ITEMS</h2>
    <ul id="item-list">
      <li v-for="item in items"
        v-bind:key="item.itemId"
        @click="itemView(item.itemId)">
        <div class="item-list-item">
          <div>{{ item.itemName }}</div>
          <span class="icon-container" v-if="isAdmin">
            <router-link
              v-bind:to="{
                name: 'addItem',
                params: { itemId: item.itemId },
              }"
            >
              ADD
            </router-link>
          </span>
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
      this.$router.push("/item");
    },
  },
};
</script>

<style>
</style>