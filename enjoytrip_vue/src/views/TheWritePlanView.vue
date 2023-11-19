<script setup>
import { useRoute } from "vue-router";
import favoritePlanWrite from ""

const checkList = useRoute().query.checkList;
console.log(checkList);

</script>

<template>
  <div>
    <h2>여행 계획</h2>

  </div>
</template>

<style scoped></style>