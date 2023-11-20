<script setup>
import { ref, watch, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { addFavorite, isFavorited } from "@/api/favorite";
import { useRouter } from "vue-router";

const router = useRouter();

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

const param = ref({
  id: null,
  userId: "",
  destinationId: null,
  attrInfo: null,
  attrDesc: null
});

const toggleFavorite = async () => {
  isFavorite.value = !isFavorite.value;
  param.value.destinationId = props.attraction.id;
  param.value.userId = userInfo.value.id;
  addFavorite(param.value, isFavorite.value, (response) => {
    // console.log(response);
  },
    (error) => {
      console.log(error);
    }
  );
  // console.log(param.value);

  // 스타일을 동적으로 변경
  starButtonStyle.value.color = isFavorite.value ? "gold" : "#ccc";
};

onMounted(() => {
  setStar();
})

watch(
  () => props.attraction.value,
  () => {
    setStar();
  },
  { deep: true }
);

const setStar = () => {
  // 컴포넌트가 마운트되면서 즐겨찾기 여부를 조회
  isFavorited(userInfo.value.id, props.attraction.id,
    (response) => {
      isFavorite.value = response.data; // API 응답에 따라 수정
      starButtonStyle.value.color = isFavorite.value ? "gold" : "#ccc";
    }, (error) => {
      console.error("Error fetching isFavorite:", error);
    });
}


// 상세 페이지 모달
const isModalOpen = ref(false);

const openModal = () => {
  isModalOpen.value = true;
};

const closeModal = () => {
  isModalOpen.value = false;
};

//글쓰기 라우터
const moveWrite = () => {
  router.push({ name: "article-write" });
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
      <button class="btn btn-outline-secondary" @click="openModal">Button</button>

      <div v-if="isModalOpen" class="modal">
        <div class="modal-content">
          <span @click="closeModal" class="close">&times;</span>
          <div class="modal-header">
            <img :src="props.attraction.img" alt="Attraction Image" class="modal-image">
          </div>
          <div class="modal-body">
            <h2>{{ props.attraction.title }}</h2>
            <p>{{ props.attraction.content }}</p>
            <p>{{ props.attraction.detail }}</p>
          </div>
          <div class="modal-footer">
            <button class="btn" :class="{ 'btn-outline-secondary': !isFavorite, 'btn-outline-warning': isFavorite }"
              @click="toggleFavorite">즐겨찾기</button>
            <button class="btn btn-outline-secondary" @click="moveWrite">글쓰기</button>
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
</style>
