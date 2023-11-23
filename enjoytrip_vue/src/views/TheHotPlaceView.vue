<script setup>
import { ref, onMounted } from 'vue';
import VCard from '@/components/common/VCard.vue';
import { listHotPlace } from "@/api/board";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();
const list = ref([]);

onMounted(() => {
  getList()
});

const getList = () => {
  // API 호출
  listHotPlace("",
    ({ data }) => {
      list.value = data;
      console.log("list", list.value);
      console.log("list", list.value);
    },
    // console.log(sidoData.value);
    (error) => {
      console.log(error);
    }
  )
};

const cardClick = (value) => {
  console.log(value);
  router.push({ name: "article-list", query: { destinationId: value } });
}

</script>

<template>
  <div>
    <div class="topPlace">
      <h2 class="my-3 py-3 text-center">Hot Place</h2>
      <div class="topPlacelist">
        <template v-for="item in list" :key="item.contentId">
          <VCard :imgSrc="item.firstImage" :title="item.title" :content="item.addr1" @click="cardClick(item.contentId)" />
        </template>
      </div>
    </div>
    <div class="place">
      <!-- <template v-for="(item, index) in 10" :key="index">

                                        <VCard :imgSrc="imgSrc" :title="title" :content="content" width="200px" />
                                      </template> -->
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
  flex-wrap: wrap;
}

.place {
  flex-wrap: wrap;
  display: flex;
  justify-content: center;

}
</style>
