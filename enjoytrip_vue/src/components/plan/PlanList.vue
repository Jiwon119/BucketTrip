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
      <div>
        <div>제목 : {{ list.title }}</div>
        <div>내용 : {{ list.content }}</div>
        <div class="div">
          <template v-for="(item, index) in list.attrInfo" :key="index">
            <VCard :title="item.title" :imgSrc="item.firstImage" :content="item.content" @click="onSelect(list)"
              width="100px" />
          </template>
        </div>
      </div>
    </div>
  </template>
</template>

<style scoped>
.favoriteCard {
  position: relative;
  background-color: rgb(199, 199, 199);
  border-radius: 10px;
  margin: 10px;
  padding: 10px;


}

.checkbox {
  position: absolute;
  left: 20px;
  top: 10px;
  z-index: 1;
}

.div {
  display: flex;
  padding: 0 30px;
  justify-content: center;
  flex-wrap: wrap;
}
</style>