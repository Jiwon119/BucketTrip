<script setup>
import { ref } from 'vue';
import VCard from '@/components/common/VCard.vue';

const imgSrc = ref('"http://tong.visitkorea.or.kr/cms/resource/01/1999501_image2_1.jpg"')
const title = ref("제목")
const content = ref("내용")

</script>

<template>
  <div>
    <div class="topPlace">
      <h2>TOP Place</h2>
      <div class="topPlacelist">
        <VCard :imgSrc="imgSrc" :title="title" :content="content" />
        <VCard :imgSrc="imgSrc" :title="title" :content="content" />
        <VCard :imgSrc="imgSrc" :title="title" :content="content" />
      </div>
    </div>
    <div class="place">
      <template v-for="(item, index) in 10" :key="index">

        <VCard :imgSrc="imgSrc" :title="title" :content="content" width="200px" />
      </template>
    </div>
  </div>
</template>

<style scoped>
.topPlace {
  border-radius: 15px 15px 0px 0px;
  padding: 15px;
}

.topPlacelist {
  display: flex;
  padding: 0 30px;
  justify-content: center;
}

.place {
  flex-wrap: wrap;
  display: flex;
  justify-content: center;

}
</style>
