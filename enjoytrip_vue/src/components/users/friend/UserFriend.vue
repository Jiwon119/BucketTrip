<script setup>
import { ref, onMounted, watch } from 'vue';
import { findUser, getFriends, addFriend } from "@/api/user";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ refreshAlert: Boolean })

watch(
  () => props.refreshAlert,
  () => {
    console.log("로그로그");
    setFriends();
  },
  { deep: true }
)

const friendProfiles = ref([]);

onMounted(() => {
  setFriends();
})

const isModalOpen = ref(false);
const searchText = ref('');
const searchResults = ref([]);
const searchOption = ref('id'); // 기본 검색 옵션은 아이디


const openModal = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
  searchText.value = '';
  searchResults.value = [];
};

const setFriends = () => {
  getFriends(
    userInfo.value.id, 1,
    ({ data }) => {
      console.log("data", data);
      friendProfiles.value = data;
    }, (error) => {
      console.error("Error fetching isFavorite:", error);
    });
};

const searchFriend = () => {
  const option = searchOption.value;
  findUser(
    { [option]: searchText.value }
    ,
    ({ data }) => {
      console.log("data", data);
      searchResults.value = data;
    }, (error) => {
      console.error("Error fetching isFavorite:", error);
    });
};

const OnAddFriend = (friend) => {
  addFriend(
    userInfo.value.id, friend.id,
    ({ data }) => {
      console.log("data", data);
      searchResults.value = data;
    }, (error) => {
      console.log(error);
    });
};

</script>

<template>
  <div class="friend-card">
    <!-- 친구 프로필... -->
    <div v-for="friend in friendProfiles" :key="friend.id" class="friend-profile">
      <div class="img-container">
        <img :src="friend.profilePicture" class="profile-img" alt="프로필 이미지" />
      </div>
      <h5>{{ friend.name }}</h5>
      <p>아이디: {{ friend.id }}</p>
    </div>
    <button class="add-friend-button" @click="openModal">+</button>

    <div v-if="isModalOpen" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <h2>친구 정보 검색</h2>
        <!-- 검색 폼과 결과 표시 영역 -->
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
            <input type="text" v-model="searchText" class="form-control" id="friendId" placeholder="검색어를 입력하세요" />
            <button class="btn btn-outline-secondary" type="button" @click="searchFriend">
              검색
            </button>
          </div>
          <div v-if="searchResults.length > 0">
            <h3>검색 결과:</h3>
            <ul class="list-group">
              <li v-for="result in searchResults" :key="result.id"
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
    </div>
  </div>
</template>
<style scoped>
.friend-card {
  min-height: 100px;
  display: flex;
  border: 1px solid #ddd;
  padding: 0 30px;
  margin: 10px;
  position: relative;
  overflow-x: auto;
}

.friend-profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0px 20px;

}

.add-friend-button {
  position: absolute;
  top: 5px;
  right: 5px;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #cecece;
  color: white;
  font-size: 18px;
  border: none;
  cursor: pointer;
}

.add-friend-button:hover {
  background-color: #c5c5c5;
  /* 호버 시 배경색이 진해지도록 설정 */
}

.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;

  overflow: auto;
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
  min-height: 300px;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
  cursor: pointer;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}



/* 상세 페이지 */

.modal-header {
  text-align: center;
}

.modal-image {
  width: 100%;
  height: auto;
  max-height: 300px;
  /* 이미지의 최대 높이 설정 */
}

.modal-body {
  padding: 20px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
}

.modal-footer button {
  margin-left: 10px;
}

.img-container {
  width: 70px;
  height: 70px;
  margin: 20px auto;
  position: relative;
  border-radius: 50%;
  overflow: hidden;
  border: rgb(203, 203, 203) solid 1px;
}

.profile-img {
  width: 100%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
