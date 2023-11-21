<script setup>
import { ref, watch } from "vue";
import { storeToRefs } from "pinia";
import { searchUserBoard } from "@/api/board"
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";
import VCard from "../common/VCard.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ planList: Array })

const emit = defineEmits(["onClickPlan"]);

const clickPlan = (val) => {
  emit("onClickPlan", props.planList.findIndex(i => i.id == val.id));
}


const clickStamp = (val) => {
  console.log(val);
  searchUserBoard(
    { userId: userInfo.value.id, destinationId: val.contentId },
    ({ data }) => {
      console.log("data", data);
      if (data.length == 0) {
        console.log(null);
      } else {
        router.push({ name: 'article-view', params: { articleno: data.articleNo } })
      }
    },
    (error) => {
      console.log(error);
    }
  );
}

</script>

<template>
  <template v-for="list in props.planList" :key="props.planList.id">
    <div class="favoriteCard">
      <div @click="clickPlan(list)">
        <div>제목 : {{ list.title }}</div>
        <div>내용 : {{ list.content }}</div>
        <template v-for="item in list.attrInfo" :key="item.id">
          <div class="div">
            <input type="button" class="btn btn-primary" @click="clickStamp(item)" :value="stamp">
            <VCard :title="item.title" :imgSrc="item.firstImage" :content="item.addr1" @click="onSelect(list)"
              width="100px" />
          </div>
        </template>
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

.div {
  display: inline-block;
  justify-content: center;
  flex-wrap: wrap;
}

.btn {
  position: relative;
  left: -40px;
  top: 60px;
  z-index: 1;
}
</style>