<script setup>
import { ref, watch } from "vue";
import VCard from "../common/VCard.vue";

const props = defineProps({ planList: Array })

watch(
  () => props.planList,
  () => console.log("--plans--", props.planList),
  { deep: true }
)

const emit = defineEmits(["onFavoriteSelect", "onCheckboxClick"]);

const onSelect = (list) => {
  console.log(list);
  emit("onFavoriteSelect", list);
};

const checkboxClick = (val) => {
  console.log(val);
  emit("onCheckboxClick", val)
}

</script>

<template>
  <template v-for="list in props.planList" :key="props.planList.id">
    <div class="favoriteCard">
      <VCard :title="list.title" :imgSrc="list.firstImage" :content="list.content" @click="onSelect(list)" />
    </div>
  </template>
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