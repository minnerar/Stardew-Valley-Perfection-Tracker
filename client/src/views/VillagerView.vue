<template>
    <div class="villager-detail">
      <div class="villager-info">
        <image>{{ villager.imageURL }}</image>
        <h2 class="item-name">{{ villager.name }}</h2>
        <p>{{ villager.description }}</p>
        <p><strong>Max Hearts? :</strong> {{  }}</p>
        <p><strong>Marriage Candidate:</strong> {{ villager.marriageCandidate }}</p>
        <p><strong>Birthday:</strong> {{ villager.birthday }}</p>
        <p><strong>Loved Gifts:</strong> {{ villager.loved }}</p>
      </div>
    </div>
  </template>

<script>
export default {
    props: {
        villager: Object
    },
    methods: {
        
    }
}
</script>

<style>

</style>