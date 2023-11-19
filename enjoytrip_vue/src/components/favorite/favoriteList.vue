<script setup>
import { ref, onMounted } from "vue";
import VCard from "../common/VCard.vue";

const props = defineProps({ favoriteList: Array })

</script>

<template>
  <template v-for="list in props.favoriteList" :key="props.favoriteList.id">
    <VCard :title="list.attrInfo.title" :imgSrc="list.attrInfo.firstImage" :content="list.attrInfo.addr1" />
  </template>
</template>

<style scoped></style>