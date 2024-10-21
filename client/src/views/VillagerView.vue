<template>
  <div class="villager-detail">
    <div class="villager-info">
      <img :src="selectedVillager.imageURL" />
      <h2 class="villager-name">{{ selectedVillager.villagerName }}</h2>
      <p>{{ selectedVillager.villagerDescription }}</p>
      <p>
        <strong>Current Hearts:</strong>
        <button v-if="isAdmin" @click.stop="decrement" :disabled="count === 0">
          -
        </button>
        <span>{{ selectedVillager.villagerHeartCounter }}</span>
        <button v-if="isAdmin" @click.stop="increment" :disabled="count === 10">
          +
        </button>
      </p>
      <p>
        <strong>Marriage Candidate:</strong>
        <span>{{ selectedVillager.villagerMarriageCandidate }}</span>
      </p>
      <p>
        <strong>Birthday:</strong>
        <span>{{ selectedVillager.villagerBirthday }}</span>
      </p>
      <p>
        <strong>Loved Gifts:</strong>
        <span>{{ removeNull(selectedVillager.villagerLovedGifts) }}</span>
      </p>
    </div>
    <!-- <div>
      <button v-if="isAdmin" @click="toggleEdit">
        {{ isEditing ? "SAVE" : "EDIT" }}
      </button>
    </div> -->
    <!-- <div>
      <button v-if="isAdmin" @click="deleteVillager(this.selectedVillager)">
        DELETE
      </button>
    </div> -->
  </div>
</template>

<script>
import { resourceService } from "../services/ResourceService";

export default {
  data() {
    return {
      count: 0,
      isEditing: false,
      // villagerObject: {
      //   villagerName: "",
      //   villagerMarriageCandidate: false,
      //   villagerBirthday: "",
      //   villagerDescription: "",
      //   villagerHeartCounter: 0,
      //   villagerLovedGifts: [],
      //   villagerId: 999
      // },
    };
  },
  // props: {
  //   villager: Object,
  // },
  methods: {
    increment() {
      if (this.count < 10) {
        // increment the counter
        this.count++;

        // save the updated counter value back to the database
        this.$store.commit("UPDATE_VILLAGER_HEART_COUNTER", {
          villager: this.selectedVillager,
          count: this.count,
        });
        this.selectedVillager.villagerHeartCounter = this.count;
        this.updateVillager(this.selectedVillager);
        console.log(this.selectedVillager.villagerHeartCounter);
      }
    },
    decrement() {
      if (this.count > 0) {
        // decrement the counter
        this.count--;

        // save the updated counter value back to the database
        this.$store.commit("UPDATE_VILLAGER_HEART_COUNTER", {
          villager: this.selectedVillager,
          count: this.count,
        });
        this.selectedVillager.villagerHeartCounter = this.count;
        this.updateVillager(this.selectedVillager);
        console.log(this.selectedVillager.villagerHeartCounter);
      }
    },
    // toggleEdit() {
    //   // toggle the isEditing variable
    //   this.isEditing = !this.isEditing;

    //   // update the information from the input values
    //   if (this.isEditing) {
    //     this.villagerObject = {
    //       villagerName: this.selectedVillager.villagerName,
    //       villagerMarriageCandidate: this.selectedVillager.villagerMarriageCandidate,
    //       villagerBirthday: this.selectedVillager.villagerBirthday,
    //       villagerDescription: this.selectedVillager.villagerDescription,
    //       villagerHeartCounter: this.selectedVillager.villagerHeartCounter,
    //       villagerLovedGifts: this.selectedVillager.villagerLovedGifts,
    //       villagerId: this.selectedVillager.villagerId
    //     };
    //   } else {
    //     this.selectedVillager.villagerName = this.villagerObject.villagerName;
    //     this.selectedVillager.villagerMarriageCandidate =  this.villagerObject.villagerMarriageCandidate;
    //     this.selectedVillager.villagerBirthday = this.villagerObject.villagerBirthday;
    //     this.selectedVillager.villagerDescription = this.villagerObject.villagerDescription;
    //     this.selectedVillager.villagerHeartCounter = this.villagerObject.villagerHeartCounter;
    //     this.selectedVillager.villagerLovedGifts = this.villagerObject.villagerLovedGifts;
    //   }

    //   // save the update villager to the database
    //   this.updateVillager(this.villagerObject);
    // },
    updateVillager(villager) {
      // update the villager
      resourceService.updateVillagerById(villager);
    },
    removeNull(giftArray) {
      // filter through their loved gifts array and only return the non-null items
      return giftArray.filter((gift) => {
        return gift != null;
      });
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
    this.count = this.selectedVillager.villagerHeartCounter;
  },
  computed: {
    selectedVillager() {
      // get the villager with the villager id passed from the click from the villagerList
      return this.$store.state.villagers.find((villager) => {
        return villager.villagerId == this.$route.params.villagerId;
      });
    },
    isAdmin() {
      return (
        //checks if the user is an admin
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
  },
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
  font-family: "Arial", sans-serif;
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