<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoute } from "vue-router";
import { searchAttractionId } from "@/api/attraction";
import favoriteMap from "./favoriteMap.vue";
import VCard from "../common/VCard.vue";
import dragList from "./plan/dragList.vue";


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
  list.value.forEach(element => {
    getArticleList(element)
  });
}

watch(
  () => favorites.value,
  () => {

  },
  { deep: true }
)


const getArticleList = (id) => {
  searchAttractionId(
    { id: id },
    ({ data }) => {
      favorites.value.push(data);
    },
    (error) => {
      console.log(error);
    }
  );
};

</script>

<template>
  <div class="row">
    <div class="col-6">
      <favoriteMap :favoriteList="favorites" :selectedList="selected" :line="true" />
    </div>
    <div class="col-6">
      <h5>즐겨찾기 목록</h5>
      <br>
      <dragList :list="favorites" />
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