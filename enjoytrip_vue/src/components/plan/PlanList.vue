<script setup>
import { ref, watch } from "vue";
import VCard from "../common/VCard.vue";

const props = defineProps({ planList: Array })

watch(
  () => props.planList,
  () => console.log("--plans--", props.planList),
  { deep: true }
)

const emit = defineEmits(["onClickPlan"]);

const clickPlan = (val) => {
  console.log(val.id);
  console.log(props.planList);
  console.log(props.planList.findIndex(i => i.id == val.id));

  emit("onClickPlan", props.planList.findIndex(i => i.id == val.id));
}

</script>

<template>
  <template v-for="list in props.planList" :key="props.planList.id">
    <div class="favoriteCard">
      <div @click="clickPlan(list)">
        <div>제목 : {{ list.title }}</div>
        <div>내용 : {{ list.content }}</div>
        <div class="div">
          <template v-for="item in list.attrInfo" :key="item.id">
            <VCard :title="item.title" :imgSrc="item.firstImage" :content="item.addr1" @click="onSelect(list)"
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