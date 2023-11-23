<script setup>
import { ref, watch, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRoute, useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { searchAttractionId } from "@/api/attraction";
import { listPlan, writePlan } from "@/api/plan"
import { findUser, getFriends, addFriend } from "@/api/user";
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
const friends = ref([])


// watch(
//   () => friendSearch.value,
//   () => {
//     // 여기에서 친구 검색 API 호출 또는 로직을 추가할 수 있습니다.
//     // 결과를 friendSearchResults에 할당하여 검색 결과가 바로 아래에 나타나도록 합니다.
//   }
// );

const OnAddFriend = (friend) => {
  // 선택한 친구를 계획에 추가하는 로직을 추가합니다.
  // 예를 들어, planInfo.friends 배열에 선택한 친구 정보를 추가하고, friendSearch를 초기화합니다.
  console.log(friends.value);
  if (friends.value.indexOf(friend.id) == -1) {
    friends.value = [...friends.value, friend.id];
  }
};

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
      planList: list.value,
      friends: friends.value
    },
    () => {
      // router.push({ name: "favorite" });
    },
    (error) => {
      console.log(error);
    }
  );
}

const friendSearch = ref("");
const friendSearchResults = ref([]);
const searchOption = ref('id'); // 기본 검색 옵션은 아이디


const searchFriend = () => {
  const option = searchOption.value;
  findUser(
    { [option]: friendSearch.value }
    ,
    ({ data }) => {
      console.log("data", data);
      friendSearchResults.value = data;
    }, (error) => {
      console.error("Error fetching isFavorite:", error);
    });
};

</script>

<template>
  <div class="row">
    <div class="col-6">
      <favoriteMap class="mb-3" :favoriteList="favorites" :line="true" />

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
      <div class="mb-3 row">
        <label for="content" class="col-sm-2 col-form-label">친구</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" v-model="friends" readonly>
        </div>
      </div>
      <div>
        <label for="friendId" class="form-label">친구 검색:</label>
        <div class="input-group mb-3">
          <div class="input-group-append">
            <select v-model="searchOption" class="form-select">
              <option value="id">아이디</option>
              <option value="email">이메일</option>
              <option value="name">이름</option>
            </select>
          </div>
          <input type="text" v-model="friendSearch" class="form-control" id="friendId" placeholder="검색어를 입력하세요" />
          <button class="btn btn-outline-secondary" type="button" @click="searchFriend">
            검색
          </button>
        </div>
        <div v-if="friendSearchResults.length > 0">
          <ul class="list-group">
            <li v-for="result in friendSearchResults" :key="result.id"
              class="list-group-item d-flex justify-content-between align-items-center">
              <span>{{ result.name }} ({{ result.id }})</span>
              <button class="btn btn-outline-secondary" @click="OnAddFriend(result)">
                추가
              </button>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="col-6">
      <h5>
        즐겨찾기 목록
        <button class="btn btn-outline-secondary" @click="createPlan"> 여행 계획 생성</button>
      </h5>

      <dragList :list="favorites" />
    </div>
  </div>
</template>

<style scoped>
label {
  font-size: 20px;
  font-weight: bold;
}

.checkbox {
  position: absolute;
  left: 20px;
  top: 10px;
  z-index: 1;
}
</style>