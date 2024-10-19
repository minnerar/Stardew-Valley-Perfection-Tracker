<template>
  <div class="item-detail">
    <div class="item-info">
      <img :src="item.imageURL" />
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
      <p>
        <strong>Classification:</strong>
        <span v-if="isEditing">
          <input v-model="item.classification" />
        </span>
        <span v-else>{{
          item.classification ? item.classification : "N/A"
        }}</span>
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
        classification: "",
      },
    };
  },
  methods: {
    toggleItemStatus() {
      // method for toggling the completion status of the item
      this.toggleStatus = !this.toggleStatus;

      // updating the completion status in the database
      this.$store.commit("UPDATE_ITEM_COMPLETION", { item: this.item, status: this.toggleStatus });
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
          classification: this.item.classification,
        };
      } else {
        // If exiting edit mode, update the current item with the values from itemObject
        this.item.imageURL = this.itemObject.imageURL;
        this.item.itemName = this.itemObject.itemName;
        this.item.itemDescription = this.itemObject.itemDescription;
        this.item.itemSeason = this.itemObject.itemSeason;
        this.item.itemTime = this.itemObject.itemTime;
        this.item.itemWeather = this.itemObject.itemWeather;
        this.item.itemLocation = this.itemObject.itemLocation;
        this.item.classification = this.itemObject.classification;

        // Save the updated item back to the store or API
        this.updateItem(this.item);
      }
    },
    updateItem(item) {
      // update the item
      resourceService.updateItemById(item);
    },
    deleteItem(item) {
      // delete the item
      resourceService.deleteItemById(item.itemId);
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
};
</script>

<style>
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