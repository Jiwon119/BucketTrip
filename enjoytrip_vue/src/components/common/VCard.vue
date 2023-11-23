<script setup>

const props = defineProps({
  imgSrc: String,
  title: String,
  content: String,
  width: String,
  height: String,
});


</script>

<template>
  <div class="card" :style="{ width: width, height: height }">
    <img v-if="imgSrc" :src="imgSrc" class="card-img-top">
    <img v-else src="https://i.ibb.co/CWZysc0/mountains.png">
    <div class=" card-body">
      <div class="card-title">{{ title }}</div>
      <div class="card-content">{{ content }}</div>
    </div>
  </div>
</template>

<style scoped>
.card {
  width: 18rem;
  margin: 10px;
  min-width: 150px;
}

.card-title {
  font-size: 20px;
  font-weight: bold;
}

.card-content {
  font-size: 15px;
}
</style>
