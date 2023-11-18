<script setup>
import VCard from '@/components/common/VCard.vue';
import { ref } from 'vue';

const imgSrc = ref('http://tong.visitkorea.or.kr/cms/resource/70/608470_image2_1.jpg')
const title = ref("제목")
const content = ref("내용")


</script>

<template>
  <div>Main 화면 입니다.</div>

  <VCard :imgSrc="imgSrc" :title="title" :content="content" width="200px" />
</template>

<style scoped></style>
