<template>
  <div class="villager-detail">
    <div class="villager-info">
      <img :src="selectedVillager.imageURL" />
      <h2 class="villager-name">{{ selectedVillager.villagerName }}</h2>
      <p>{{ selectedVillager.villagerDescription }}</p>
      <p>
        <strong>Current Hearts:</strong>
        <button v-if="isAdmin" @click="decrement" :disabled="count === 0">-</button>
        <span>{{ count }}</span>
        <button v-if="isAdmin" @click="increment" :disabled="count === 8">+</button>
      </p>
      <p>
        <strong>Marriage Candidate:</strong>
        <span v-if="!isEditing">{{ selectedVillager.villagerMarriageCandidate }}</span>
        <input v-if="isEditing" v-model="updatedMarriageCandidate" />
      </p>
      <p><strong>Birthday:</strong> 
        <span v-if="!isEditing">{{ selectedVillager.villagerBirthday }}</span>
        <input v-if="isEditing" v-model="updatedBirthday" />
      </p>
      <p><strong>Loved Gifts:</strong>
        <span v-if="!isEditing">{{ removeNull(selectedVillager.villagerLovedGifts) }}</span>
        <input v-if="isEditing" v-model="updatedLovedGifts" />
      </p>
    </div>
    <div>
      <button v-if="isAdmin" @click="toggleEdit">{{ isEditing ? 'SAVE' : 'UPDATE' }}</button>
    </div>
    <div><button v-if="isAdmin" @click="deleteVillager(this.selectedVillager)">DELETE</button></div>
  </div>
</template>

<script>
import { resourceService } from '../services/ResourceService';

export default {
  data() {
    return {
      count: 0,
      isEditing: false,
      villagerObject: {
        villagerName: "",
        villagerMarriageCandidate: false,
        villagerBirthday: "",
        villagerDescription: "",
        villagerHeartCounter: 0,
        villagerLovedGifts: []
      }
    }
  },
  props: {
    villager: Object,
  },
  methods: {
    toggleEdit() {
      // toggle the isEditing variable
      this.isEditing = !this.isEditing;

      // update the information from the input values
      if (this.isEditing) {
        this.villagerObject = {
          villagerName: this.villager.villagerName,
          villagerMarriageCandidate: this.villager.villagerMarriageCandidate,
          villagerBirthday: this.villager.villagerBirthday,
          villagerDescription: this.villager.villagerDescription,
          villagerHeartCounter: this.villager.villagerHeartCounter,
          villagerLovedGifts: this.villager.villagerLovedGifts
        };
      } else {
        this.villagerObject.villagerName = this.selectedVillager.villagerMarriageCandidate;
        this.villagerObject.villagerMarriageCandidate = this.selectedVillager.villagerMarriageCandidate;
        this.villagerObject.villagerBirthday = this.selectedVillager.villagerBirthday;
        this.villagerObject.villagerDescription = this.selectedVillager.villagerDescription
        this.villagerObject.villagerHeartCounter = this.selectedVillager.villagerHeartCounter
        this.villagerObject.villagerLovedGifts = this.selectedVillager.villagerLovedGifts.join(', ');
      }

      // save the update villager to the database
      this.updateVillager(this.selectedVillager);
      
    },
    updateVillager(villager) {
      // update the villager
      resourceService.updateVillagerById(villager);

    },
    deleteVillager(villager) {
      // delete the villager
      resourceService.deleteVillagerById(villager.id);
    },
    updateVillagerHeartCounter(villager, count) {
      // update the villager heart counter 
      this.$store.commit("UPDATE_VILLAGER_HEART_COUNTER", { villager: this.selectedVillager, count: this.count});
      this.selectedVillager.villagerHeartCounter = this.count;

      // save the updated counter value back to the database
      resourceService.updateVillagerById(this.selectedVillager);

    },
    increment() {
      if (this.count < 10) {
        // increment the counter
        this.count++;

        // save the updated counter value back to the database
        this.updateVillagerHeartCounter(this.selectedVillager, this.count);
      }
    },
    decrement() {
      if (this.count > 0) {
        // decrement the counter
        this.count--;

        // save the updated counter value back to the database
        this.updateVillagerHeartCounter(this.selectedVillager, this.count);
      }
    },
    removeNull(giftArray) {
      // filter through their loved gifts array and only return the non-null items 
      return giftArray.filter( gift => {
        return gift != null;
      });
    }
  },
  computed: {
    selectedVillager() {
      // get the villager with the villager id passed from the click from the villagerList
      return this.$store.state.villagers.find( villager => {
        return villager.villagerId == this.$route.params.villagerId;
      });
    },
    isAdmin() {
      return (
        //checks if the user is an admin
        this.$store.state.user &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
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