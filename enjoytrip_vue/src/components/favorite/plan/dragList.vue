<script>
import { ref } from 'vue';
import draggable from "vuedraggable";
import VCard from "@/components/common/VCard.vue";

export default {
  props: {
    list: Array
  },
  components: {
    draggable,
    VCard
  },
  methods: {
    openModal() {
      this.isModalOpen = true;
    }
  },
  data() {
    return {
      enabled: true,
      list: this.list,
      dragging: false,
      isModalOpen: false
    };
  }
};


</script>

<template>
  <div class="container">
    <draggable :list="list" :disabled="!enabled" item-key="name" class="list-group" @start="dragging = true"
      @end="dragging = false">
      <template #item="{ element }">
        <div class="list-group-item" :class="{ 'not-draggable': !enabled }">
          <!-- <img :src="element.img"> -->
          <h5>{{ list.indexOf(element) + 1 }}. {{ element.title }}</h5>
          <p>{{ element.addr1 }}</p>
          <button class="btn btn-outline-secondary" @click="openModal(element)"> 상세 페이지</button>

          <div v-if="isModalOpen" class="modal">
            <div class="modal-content">
              <span @click="isModalOpen = false" class="close">&times;</span>
              <div class="modal-header">
                <img :src="element.firstImage" alt="Attraction Image" class="modal-image">
              </div>
              <div class="modal-body">
                <h2>{{ element.title }}</h2>
                <p>{{ element.addr1 }}</p>
                <p>{{ element.attractionDescriptionDto.overview }}</p>
              </div>
            </div>
          </div>
        </div>
      </template>
    </draggable>


  </div>
</template>


<style scoped>
.container {
  height: 900px;
  overflow: auto;
}

.container::-webkit-scrollbar {
  /* display: none; */
}

.container::-webkit-scrollbar {
  width: 8px;
}

.container::-webkit-scrollbar-thumb {
  background-color: #d4d4d4;
  /* 스크롤바 색상 */
  border-radius: 5px;
  /* 스크롤바 모서리 둥글게 */
}

.container::-webkit-scrollbar-track {
  background-color: #f1f1f1;
  /* 스크롤바 트랙 색상 */
}

.list-group-item {
  padding: 30px;

}

.buttons {
  margin-top: 35px;
}

h5 {
  font-weight: bold;
}

.not-draggable {
  cursor: no-drop;
}

img {
  width: 100px;
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