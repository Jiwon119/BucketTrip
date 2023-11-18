<script setup>

const props = defineProps({ imgSrc: String, title: String, content: String });


</script>

<template>
  <div class="card" style="width: 18rem;">
    <img :src="imgSrc" class="card-img-top" alt="...">
    <div class="card-body">
      <div class="card-title">{{ title }}</div>
      <div class="card-content">{{ content }}</div>
    </div>
  </div>
</template>

<style scoped>
.card {
  margin: 10px;
}

.card-title {
  font-size: 25px;
  font-weight: bold;
}

.card-content {}
</style>
