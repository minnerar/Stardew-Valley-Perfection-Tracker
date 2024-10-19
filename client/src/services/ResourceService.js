import axios from 'axios';

const resourceService = {
    // methods for Achievements 
    getAchievements() {
        return axios.get('/achievements');
    },
    getAchievementById(id) {
        return axios.get(`/achievements/${id}`);
    },
    addAchievement(achievement) {
        return axios.post('/achievements', achievement);
    },
    updateAchievementById(achievement) {
        return axios.put(`/achievements/${achievement.id}`, achievement);
    },
    deleteAchievementById(id) {
        return axios.delete(`/achievements/${id}`);
    },

    // methods for Items
    getItems() {
        return axios.get('/items');
    },
    getItemById(id) {
        return axios.get(`/items/${id}`);
    },
    addItem(item) {
        return axios.post('/items', item);
    },
    updateItemById(item) {
        return axios.put(`/items/${item.itemId}`, item);
    },
    deleteItemById(id) {
        return axios.delete(`/items/${id}`);
    },

    // methods for Villagers 
    getVillagers() {
        return axios.get('/villagers');
    },
    getVillagerById(id) {
        return axios.get(`/villagers/${id}`);
    },
    updateVillagerById(villager) {
        return axios.put(`/villagers/${villager.id}`, villager);
    },
    deleteVillagerById(villager) {
        return axios.delete(`/villagers/${villager.id}`);
    },

    // methods for Classifications  
    getClassifications() {
        return axios.get('/classifications');
    },
};

export { resourceService };