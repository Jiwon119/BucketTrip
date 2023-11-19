<script setup>
import favoriteMap from '@/components/favorite/favoriteMap.vue';
import favoriteList from '@/components/favorite/favoriteList.vue'

import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { listFavorite } from "@/api/favorite";
import { useMemberStore } from "@/stores/member";

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
      console.log(data.favoriteList);
      favorites.value = data.favoriteList;
      console.log("favorites", favorites.value);
    },
    (error) => {
      console.log(error);
    }
  );
}
</script>

<template>
  <div>
    <h2>북마크 정보</h2>
    <div class="row">
      <div class="col-8">
        <favoriteMap :favoriteList="favorites" />
      </div>
      <div class="col-4">
        <favoriteList :favoriteList="favorites" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>