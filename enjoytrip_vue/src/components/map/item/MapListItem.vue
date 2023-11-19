<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ attraction: Object });

// 즐겨찾기 관련
const isFavorite = ref(false);
const starButtonStyle = ref({
  cursor: "pointer",
  fontSize: "1.5em",
  border: "none",
  background: "transparent",
  color: "#ccc",
});

const toggleFavorite = () => {
  isFavorite.value = !isFavorite.value;
  console.log(props.attraction.id);
  console.log(userInfo.value);
  console.log(isFavorite.value);

  // 스타일을 동적으로 변경
  starButtonStyle.value.color = isFavorite.value ? "gold" : "#ccc";
};

const openDetail = () => {
  console.log(props.attraction.title);
  console.log("상세페이지");
};

// 상세 페이지 모달
const isModalOpen = ref(false);

const openModal = () => {
    isModalOpen.value = true;
};

const closeModal = () => {
    isModalOpen.value = false;
};
</script>

<template>
  <tr class="text-center">
    <td>
      <img :src="props.attraction.img">
    </td>
    <td>
      <button id="starButton" @click="toggleFavorite" :style="starButtonStyle">
        ★
      </button>
    </td>
    <td>
      <p>{{ props.attraction.title }}</p>
      {{ props.attraction.content }}
    </td>
    <td>
      <button @click="openModal">Button</button>

        <div v-if="isModalOpen" class="modal">
            <div class="modal-content">
                <span @click="closeModal" class="close">&times;</span>
                <div class="modal-header">
                <img :src="props.attraction.img" alt="Attraction Image" class="modal-image">
                </div>
                <div class="modal-body">
                <h2>{{ props.attraction.title }}</h2>
                <p>{{ props.attraction.content }}</p>
                <!-- <p>{{props.attraction.}}</p> -->
                </div>
                <div class="modal-footer">
                <button @click="button1Click">버튼1</button>
                <button @click="button2Click">버튼2</button>
                </div>
            </div>
        </div>

    </td>
  </tr>
</template>

<style scoped>
img {
  width: 70px;
  height: 70px;
}

p {
  margin-top: 3px;
  font-weight: bold;
}


/* 모달 스타일 */

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
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
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
  max-height: 300px; /* 이미지의 최대 높이 설정 */
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

</style>
