<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { searchAttractionId } from "@/api/attraction";
import favoriteList from "./favoriteList.vue";
import favoriteMap from "./favoriteMap.vue";
import VCard from "../common/VCard.vue";

const list = ref(useRoute().query.checkList);

const favorites = ref([]);
const selected = ref({})

const selectedList = (val) => {
  selected.value = val;
}
onMounted(() => {
  setData();
})

const setData = () => {
  console.log("setData 시작  list:", list.value);
  list.value.forEach(element => {
    console.log("---------------------", element);
    getArticleList(element)
  });
}


const getArticleList = (id) => {
  searchAttractionId(
    { id: id },
    ({ data }) => {
      console.log(data);
      favorites.value.push({
        id: data.contentId,
        mapY: data.latitude,
        mapX: data.longitude,
        title: data.title,
        content: data.addr1,
        img: data.firstImage
      });
      console.log(favorites.value);
    },
    (error) => {
      console.log(error);
    }
  );
};



</script>

<template>
  <div class="row">
    <div class="col-8">
      <favoriteMap :favoriteList="favorites" :selectedList="selected" />
    </div>
    <div class="col-4">
      즐겨찾기 목록
      <div class="row">
        <div class="col" v-for="(item, index) in favorites" :key="favorites.id">
          {{ item.id }}
        </div>
      </div>

      <template v-for="list in favorites" :key="favorites.id">
        <div class="favoriteCard">
          <VCard :title="list.title" :content="list.content" @click="onSelect(list)" />
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped>
.favoriteCard {
  position: relative;
}

.checkbox {
  position: absolute;
  left: 20px;
  top: 10px;
  z-index: 1;
}
</style>