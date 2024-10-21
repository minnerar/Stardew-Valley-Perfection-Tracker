<template>
  <div class="achievement-detail">
    <div class="achievement-info">
      <img :src="achievement.imageURL" id="achievement-image" />
      <h2 class="achievement-name">
        <template v-if="isEditing">
          <input
            v-model="achievementObject.achievementName"
            placeholder="Achievement Name"
          />
        </template>
        <template v-else>
          {{ achievement.achievementName }}
        </template>
      </h2>

      <p>
        {{
          achievement.achievementProgress == 100 ? "Completed" : "Incomplete"
        }}
      </p>
      <button v-if="isAdmin" @click="toggleAchievementStatus">
        Mark as {{ toggleStatus ? "Incomplete" : "Complete" }}
      </button>

      <p>
        <template v-if="isEditing">
          <textarea
            v-model="achievementObject.achievementDescription"
            placeholder="Achievement Description"
          ></textarea>
        </template>
        <template v-else>
          {{ achievement.achievementDescription }}
        </template>
      </p>

      <p>
        <strong>Total Needed: </strong>
        <template v-if="isEditing">
          <input
            type="number"
            v-model="achievementObject.achievementTotalNeeded"
            placeholder="Total Needed"
          />
        </template>
        <template v-else>
          {{ achievement.achievementTotalNeeded }}
        </template>
      </p>

      <p>
        <strong>Progress: </strong>
        <template v-if="isEditing">
          <input
            type="number"
            v-model="achievementObject.achievementProgress"
            placeholder="Progress"
          />
        </template>
        <template v-else>
          {{ achievement.achievementProgress }}
        </template>
      </p>

      <p>
        <strong v-if="isAdmin">Update Progress:</strong>
        <button v-if="isAdmin" @click="decrement" :disabled="count === 0">
          -
        </button>
        <span v-if="isAdmin">{{ achievement.achievementProgress }}</span>
        <button
          v-if="isAdmin"
          @click="increment"
          :disabled="count === achievement.achievementTotalNeeded"
        >
          +
        </button>
      </p>

      <div class="button-container" v-if="isAdmin">
        <div>
          <button @click="toggleEditing">
            {{ isEditing ? "SAVE" : "EDIT" }}
          </button>
        </div>
        <div>
          <button v-if="isAdmin" @click="deleteAchievement(achievement.achievementId)">
            DELETE
          </button>
        </div>
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
      count: 0,
      isEditing: false,
      achievementObject: {
        achievementName: "",
        achievementProgress: 0,
        achievementDescription: "",
        achievementTotalNeeded: 0,
        achievementCurrent: 0,
      },
    }
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
    toggleAchievementStatus() {
      // toggle the achievement status between complete and incomplete
      this.toggleStatus = !this.toggleStatus;

      // update the database with the new status
      this.$store.commit("UPDATE_ACHIEVEMENT_COMPLETION", {
        achievement: this.achievement,
        achievementTotalNeeded: this.achievementTotalNeeded,
      });
      this.achievement.achievementProgress = this.achievementTotalNeeded;
      this.updateAchievement(this.achievement);
    },
    toggleEditing() {
      // toggle the editing status
      this.isEditing = !this.isEditing;

      if (this.isEditing) {
        // If entering edit mode, copy the current achievement's values to achievementObject
        this.achievementObject = {
          achievementName: this.achievement.achievementName,
          achievementProgress: this.achievement.achievementProgress,
          achievementDescription: this.achievement.achievementDescription,
          achievementTotalNeeded: this.achievement.achievementTotalNeeded,
          achievementCurrent: this.achievement.achievementCurrent,
        };
      } else {
        // If exiting edit mode, update the current achievement with the values from achievementObject
        this.achievement.achievementName =
          this.achievementObject.achievementName;
        this.achievement.achievementProgress =
          this.achievementObject.achievementProgress;
        this.achievement.achievementDescription =
          this.achievementObject.achievementDescription;
        this.achievement.achievementTotalNeeded =
          this.achievementObject.achievementTotalNeeded;
        this.achievement.achievementCurrent =
          this.achievementObject.achievementCurrent;

        // Save the updated achievement back to the store or API
        this.updateAchievement(this.achievement);
      }
    },
    updateAchievement(achievement) {
      // update the achievement
      resourceService.updateAchievementById(achievement);
    },
    deleteAchievement(id) {
      // delete the achievement
      resourceService.deleteAchievementById(id);
      this.$router.push({
        name: "home"
      });
    },
    setProgress(input) {
      // set the achievement progress
      this.achievement.achievementProgress == input;
    },
    increment() {
      if (this.count < this.achievement.achievementTotalNeeded) {
        // increment the counter
        this.count++;

        // update in the database
        this.$store.commit("UPDATE_ACHIEVEMENT_PROGRESS", {
          achievement: this.achievement,
          count: this.count,
        });
        this.achievement.achievementProgress = this.count;
        this.updateAchievement(this.achievement);
      }
    },
    decrement() {
      if (this.count > 0) {
        // decrement the counter
        this.count--;

        // update in the database
        this.$store.commit("UPDATE_ACHIEVEMENT_PROGRESS", {
          achievement: this.achievement,
          count: this.count,
        });
        this.achievement.achievementProgress = this.count;
        this.updateAchievement(this.achievement);
      }
    },
  },
  computed: {
    achievement() {
      // get the achievement to display
      return this.$store.state.achievements.find((achievement) => {
        return achievement.achievementId == this.$route.params.achievementId;
      });
    },
    isAdmin() {
      return (
        // check if the user is an admin
        this.$store.state.user &&
        this.$store.state.user.role &&
        this.$store.state.user.role.includes("ROLE_ADMIN")
      );
    },
  },
};
</script>

<style>
.achievement-detail {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  font-family: "Arial", sans-serif;
}

.achievement-info {
  text-align: center;
}

.achievement-info img {
  max-width: 100%;
  height: auto; /* Maintain aspect ratio */
  border-radius: 8px;
  margin-bottom: 15px;
  display: block;
  margin-left: auto;
  margin-right: auto;
}

.achievement-name {
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

#achievement-image {
  max-width: 175px;
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