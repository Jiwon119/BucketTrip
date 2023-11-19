<script setup>
import { ref, onUpdated } from "vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const props = defineProps({ favoriteList: Array })

const attractionData = ref([]);

onUpdated(() => {
  console.log("--------------props.favoriteList", props.favoriteList);
  props.favoriteList.forEach(element => {
    console.log("element", element);

    attractionData.value.push({
      id: element.attrInfo.contentId,
      title: element.attrInfo.title,
      content: element.attrInfo.addr1,
      mapY: element.attrInfo.latitude,
      mapX: element.attrInfo.longitude,
      img: element.attrInfo.firstImage
    });
  });
  console.log("attractionData", attractionData.value);

})



const selected = ref({
  contentId: '',
  mapX: '',
  mapY: '',
  title: '',
  firstImage: '',
  addr: '',
});

</script>

<template>
  <div>
    <VKakaoMap :data="attractionData" :selected="selected" />
  </div>
</template>

<style scoped></style>