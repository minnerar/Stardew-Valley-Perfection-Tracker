<template>
  <div class="item-detail">
    <div class="item-info">
      <img :src="item.imageURL" id="item-image" />
      <h2 class="item-name">
        <span v-if="isEditing"> Name: <input v-model="item.itemName" /> </span>
        <span v-else>{{ item.itemName }}</span>
      </h2>
      <p>{{ toggleStatus ? "Completed" : "Incomplete" }}</p>
      <button v-if="isAdmin" @click="toggleItemStatus">
        Mark as {{ toggleStatus ? "Incomplete" : "Complete" }}
      </button>
      <p>
        <span v-if="isEditing">
          Description: <input v-model="item.itemDescription" />
        </span>
        <span v-else>{{ item.itemDescription }}</span>
      </p>
      <p>
        <strong>Season:</strong>
        <span v-if="isEditing">
          <input v-model="item.itemSeason" />
        </span>
        <span v-else>{{ item.itemSeason ? item.itemSeason : "N/A" }}</span>
      </p>
      <p>
        <strong>Time:</strong>
        <span v-if="isEditing">
          <input v-model="item.itemTime" />
        </span>
        <span v-else>{{ item.itemTime ? item.itemTime : "N/A" }}</span>
      </p>
      <p>
        <strong>Weather:</strong>
        <span v-if="isEditing">
          <input v-model="item.itemWeather" />
        </span>
        <span v-else>{{ item.itemWeather ? item.itemWeather : "N/A" }}</span>
      </p>
      <p>
        <strong>Location:</strong>
        <span v-if="isEditing">
          <input v-model="item.itemLocation" />
        </span>
        <span v-else>{{ item.itemLocation ? item.itemLocation : "N/A" }}</span>
      </p>
    </div>
    <div class="button-container" v-if="isAdmin">
      <div>
        <button @click="toggleEditing">
          {{ isEditing ? "SAVE" : "EDIT" }}
        </button>
      </div>
      <div>
        <button v-if="isAdmin" @click="deleteItem(this.item)">DELETE</button>
      </div>
    </div>
  </div>
</template>

<script>
import { resourceService } from "../services/ResourceService";

export default {
  data() {
    return {
      toggleStatus: false,
      isEditing: false,
      itemObject: {
        imageURL: "",
        itemName: "",
        itemDescription: "",
        itemSeason: "",
        itemTime: "",
        itemWeather: "",
        itemLocation: "",
        itemCompleted: false,
        itemId: 999,
      },
    };
  },
  methods: {
    toggleItemStatus() {
      // method for toggling the completion status of the item
      this.toggleStatus = !this.toggleStatus;

      // updating the completion status in the database
      this.$store.commit("UPDATE_ITEM_COMPLETION", {
        item: this.item,
        status: this.toggleStatus,
      });
      this.item.itemCompleted = this.toggleStatus;
      this.updateItem(this.item);
    },
    toggleEditing() {
      // toggling the editing status
      this.isEditing = !this.isEditing;

      // updating the itemObject values
      if (this.isEditing) {
        // If entering edit mode, copy the current item's values to itemObject
        this.itemObject = {
          imageURL: this.item.imageURL,
          itemName: this.item.itemName,
          itemDescription: this.item.itemDescription,
          itemSeason: this.item.itemSeason,
          itemTime: this.item.itemTime,
          itemWeather: this.item.itemWeather,
          itemLocation: this.item.itemLocation,
          itemCompleted: this.item.itemCompleted,
        };
      } else {
        // If exiting edit mode, update the current item with the values from itemObject
        this.itemObject.imageURL = this.item.imageURL;
        this.itemObject.itemName = this.item.itemName;
        this.itemObject.itemDescription = this.item.itemDescription;
        this.itemObject.itemSeason = this.item.itemSeason;
        this.itemObject.itemTime = this.item.itemTime;
        this.itemObject.itemWeather = this.item.itemWeather;
        this.itemObject.itemLocation = this.item.itemLocation;
        this.itemObject.itemCompleted = this.item.itemCompleted;
        this.itemObject.itemId = this.item.itemId;

        // Save the updated item back to the store or API
        this.updateItem(this.itemObject);
      }
    },
    updateItem(item) {
      console.log(item);
      // update the item
      resourceService.updateItemById(item);
    },
    deleteItem(item) {
      // delete the item
      resourceService.deleteItemById(item.itemId);
      this.$router.push({
        name: "home",
      });
    },
  },
  computed: {
    item() {
      // get the item from the store
      return this.$store.state.items.find((item) => {
        return item.itemId == this.$route.params.itemId;
      });
    },
    isAdmin() {
      return (
        // checks if the user is an admin
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
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
};
</script>

<style>
* {
  box-sizing: border-box;
}

body {
  margin: 0;
  overflow-x: hidden;
}

.item-detail {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: "Arial", sans-serif;
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

#item-image {
  max-width: 175px;
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

/* Mobile View for screens smaller than 425px */
@media (max-width: 425px) {
  .item-detail {
    padding: 15px;
    margin: 10px;
    box-shadow: none;
    font-size: 0.8em;
  }

  .item-name {
    font-size: 1em;
  }

  button {
    font-size: 0.9em;
    padding: 8px 12px;
  }

  #item-image {
    max-width: 100px;
  }
}
</style>
