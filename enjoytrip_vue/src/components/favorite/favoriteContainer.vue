<script setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import { listFavorite } from "@/api/favorite";
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";

import favoriteList from "./favoriteList.vue";
import favoriteMap from "./favoriteMap.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const favorites = ref([]);

onMounted(() => {
  getFavoriteList();
});


const getFavoriteList = () => {
  listFavorite(
    userInfo.value.id,
    ({ data }) => {
      console.log("data.favoriteList------", data.favoriteList)
      data.favoriteList.forEach(element => {
        const tmp = element.attrInfo;
        tmp.overview = element.attrDesc.overview;

        favorites.value.push(tmp);
      });
      console.log("favorites", favorites.value)

    },
    (error) => {
      console.log(error);
    }
  );
}

const selected = ref({})
const checkbox = ref([])

const selectedList = (val) => {
  selected.value = val;
}

const onCheckboxClick = (value) => {
  var idx = checkbox.value.indexOf(value.contentId);
  if (idx == -1) {
    checkbox.value.push(value.contentId)
  } else {
    checkbox.value.splice(idx, 1);
  }
}
// { name: 'Query', query: { name: 'Query 선언적 방식', age: 1 } }
const moveWrite = () => {
  router.push({ name: "writePlan", query: { checkList: checkbox.value } });
};
</script>

<template>
  <div class="row">
    <div class="col-8">
      <favoriteMap :favoriteList="favorites" :selectedList="selected" />
    </div>
    <div class="col-4">
      <button class="btn" @click="moveWrite">여행 생성</button>
      <favoriteList :favoriteList="favorites" @onFavoriteSelect="selectedList" @onCheckboxClick="onCheckboxClick" />
    </div>
  </div>
</template>

<style scoped></style>