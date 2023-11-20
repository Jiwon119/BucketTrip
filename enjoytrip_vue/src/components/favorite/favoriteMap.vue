<script setup>
import { ref, watch } from "vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const props = defineProps({ favoriteList: Array, selectedList: Object, line: Boolean })
console.log("props.favoriteList.value", props.favoriteList)

const attractionData = ref([]);

const selected = ref({
  id: '',
  mapX: '',
  mapY: '',
  title: '',
  img: '',
  content: '',
});

watch(
  () => props.selectedList,
  () => {
    selected.value = {
      id: props.selectedList.contentId,
      mapY: props.selectedList.latitude,
      mapX: props.selectedList.longitude,
      title: props.selectedList.title,
      content: props.selectedList.addr1,
      img: props.selectedList.firstImage,
    }
  }
)

watch(
  () => props.favoriteList,
  () => {
    attractionData.value = [];
    props.favoriteList.forEach(element => {
      attractionData.value.push({
        id: element.contentId,
        mapY: element.latitude,
        mapX: element.longitude,
        title: element.title,
        content: element.addr1,
        img: element.firstImage
      });
    });
    console.log("attractionData", attractionData.value);
  },
  { deep: true }
)

</script>

<template>
  <div>
    <VKakaoMap :data="attractionData" :selected="selected" :line="line" />
  </div>
</template>

<style scoped></style>