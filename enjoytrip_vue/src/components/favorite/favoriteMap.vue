<script setup>
import { ref, watch } from "vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const props = defineProps({ favoriteList: Array, selectedList: Object })

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
    props.favoriteList.forEach(element => {
      attractionData.value.push({
        id: element.attrInfo.contentId,
        mapY: element.attrInfo.latitude,
        mapX: element.attrInfo.longitude,
        title: element.attrInfo.title,
        content: element.attrInfo.addr1,
        img: element.attrInfo.firstImage
      });
    });
  }
)

</script>

<template>
  <div>
    <VKakaoMap :data="attractionData" :selected="selected" />
  </div>
</template>

<style scoped></style>