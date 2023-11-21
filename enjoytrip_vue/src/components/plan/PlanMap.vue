<script setup>
import { ref, watch } from "vue";
import VKakaoMap from "@/components/common/VKakaoMap.vue";

const props = defineProps({ planList: Array, line: Boolean })

const planData = ref([]);

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
  () => props.planList,
  () => {
    planData.value = [];
    if (props.planList != null) {
      props.planList.forEach(element => {
        planData.value.push({
          id: element.contentId,
          mapY: element.latitude,
          mapX: element.longitude,
          title: element.title,
          content: element.addr1,
          img: element.firstImage
        });
      });
    }

  },
  { deep: true }
)

</script>

<template>
  <div>
    <VKakaoMap :data="planData" :selected="selected" :line="true" />
  </div>
</template>

<style scoped></style>