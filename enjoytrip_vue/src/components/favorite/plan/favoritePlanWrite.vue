<script setup>
import { ref, watch, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRoute, useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { searchAttractionId } from "@/api/attraction";
import { listPlan, writePlan } from "@/api/plan"
import favoriteMap from "../favoriteMap.vue";
import dragList from "./dragList.vue";

const router = useRouter();

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const list = ref(useRoute().query.checkList);


const favorites = ref([]);
const planInfo = ref({
  userId: userInfo.value.id,
  title: "",
  content: "",
});


watch(
  () => favorites.value,
  () => {
    list.value = [];
    favorites.value.forEach(element => {
      list.value.push(element.contentId)
    });

    console.log("favorites", favorites.value)
    console.log("list", list.value)
  },
  { deep: true }
)

onMounted(() => {
  setData();
})

const setData = () => {
  list.value.forEach(element => {
    getArticleList(element)
  });
}

const getArticleList = (id) => {
  searchAttractionId(
    { id: id },
    ({ data }) => {
      favorites.value.push(data);
      console.log(favorites.value);
    },
    (error) => {
      console.log(error);
    }
  );
};

const createPlan = () => {
  writePlan(
    {
      planInfo: planInfo.value,
      planList: list.value
    },
    ({ data }) => {
      alert("저장이 완료되었습니다.")
      router.push({ name: "favorite" });

    },
    (error) => {
      console.log(error);
    }
  );
}

// const planList = () => {
//   listPlan(
//     userInfo.value.id,
//     ({ data }) => {
//       console.log(data);

//     },
//     (error) => {
//       console.log(error);
//     }
//   );
// }

</script>

<template>
  <div class="row">
    <div class="col-6">
      <favoriteMap :favoriteList="favorites" :line="true" />
    </div>
    <div class="col-6">
      <h5>
        즐겨찾기 목록
        <button class="btn btn-outline-secondary" @click="createPlan"> 여행 계획 생성</button>
      </h5>

      <div class="mb-3 row">
        <label for="staticEmail" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" v-model="planInfo.title">
        </div>
      </div>
      <div class="mb-3 row">
        <label for="content" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">
          <textarea class="form-control" rows="3" v-model="planInfo.content"></textarea>
        </div>
      </div>

      <dragList :list="favorites" />
    </div>
  </div>
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