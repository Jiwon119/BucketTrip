<script setup>
import { ref, onMounted } from "vue";
import VCard from "../common/VCard.vue";

const props = defineProps({ favoriteList: Array })
const emit = defineEmits(["onFavoriteSelect", "onCheckboxClick"]);

const onSelect = (list) => {
  emit("onFavoriteSelect", list);
};

const checkboxClick = (val) => {
  console.log(val);
  emit("onCheckboxClick", val)
}

</script>

<template>
  <template v-for="list in props.favoriteList" :key="props.favoriteList.id">
    <div class="favoriteCard">
      <input type="checkbox" class="form-check-input checkbox" @click="checkboxClick(list.attrInfo)">
      <VCard :title="list.attrInfo.title" :imgSrc="list.attrInfo.firstImage" :content="list.attrInfo.addr1"
        @click="onSelect(list.attrInfo)" />
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