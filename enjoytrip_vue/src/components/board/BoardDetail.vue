<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { detailArticle, deleteArticle, updateLikes } from "@/api/board";
import { searchAttractionId } from "@/api/attraction";
import BoardComment from "./item/BoardComment.vue";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const route = useRoute();
const router = useRouter();

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const { articleno } = route.params;

const articles = ref({});
const comments = ref([]);

const attraction = ref({});

onMounted(() => {
  getArticle();
  console.log(articles.value);
});

const getArticle = () => {
  console.log(articleno + "번글 얻으러 가자!!!");
  // API 호출
  detailArticle(articleno, ({ data }) => {
    console.log(JSON.stringify(data));
    articles.value = data.article;
    comments.value = data.comment;
    searchAttractionId(
      { id: articles.value.destinationId },
      ({ data }) => {
        console.log(data.title + "gdggddgd" + JSON.stringify(data));
        attraction.value = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
    (error) => {
      console.log(error);
    })
};

function moveList() {
  router.push({ name: "article-list" });
}

function moveModify() {
  router.push({ name: "article-modify", params: { articleno } });
}

function onDeleteArticle() {
  // API 호출
  deleteArticle(articleno, ({ data }) => {
    console.log(data);
  },
    (error) => {
      console.log(error);
    })
  router.push({ name: "article-list" });
}

const onRecommend = () => {
  updateLikes(articleno, () => {
    articles.value.likes = articles.value.likes + 1;
    console.log("추천 성공");
  },
    (error) => {
      console.log(error);
    });
};

</script>

<template>
  <div class="container">
    <div class="position-relative"> <!-- 부모 컴포넌트에 상대적인 위치 설정 -->
      <!-- 상단 고정 이미지 및 텍스트 -->
      <div class="fixed-top-container position-absolute top-0 end-0"> <!-- 상대적인 위치 설정 -->
        <div class="col-md align-self-center text-end">
          <div class="image-container">
            <img :src="attraction.firstImage" class="img-fluid rounded" alt="Attraction Image">
            <p class="fst-italic mt-2">{{ attraction.title }}</p>
          </div>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-lg-10 text-start">
        <div class="row my-2">
          <div class="col-8">
            <h2 class="text-secondary">{{ articles.subject }}</h2>
            <div class="clearfix align-content-center">
              <p>
                <span class="fw-bold">{{ articles.userName }}</span> <br />
                <span class="text-secondary fw-light">
                  {{ articles.registerTime }} | 조회 : {{ articles.hit + 1 }}
                </span>
              </p>
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md">

            <div class="text-secondary">
              <div v-html="articles.content"></div>
              <p>이 글이 도움이 되었다면?</p>
              <button type="button" class="btn btn-outline-secondary mb-3" @click="onRecommend">
                <font-awesome-icon icon="fa-solid fa-thumbs-up" /> {{ articles.likes }}
              </button>
            </div>
            <hr>
            <div class="mt-3">
              <template v-if="userInfo != null">
                <BoardComment />
              </template>
              <template v-else>
                <BoardComment />
              </template>
            </div>
          </div>
        </div>
        <div class="divider mt-3 mb-3"></div>

        <div class="row">
          <div class="col-md-8">
            <div class="d-flex justify-content-end">
              <button type="button" class="btn btn-outline-secondary mb-3" @click="moveList">
                글목록
              </button>
              <button type="button" class="btn btn-outline-secondary mb-3 ms-1"
                v-if="userInfo != null && articles.userId === userInfo.id" @click="moveModify">
                글수정
              </button>
              <button type="button" class="btn btn-outline-secondary mb-3 ms-1"
                v-if="userInfo != null && articles.userId === userInfo.id" @click="onDeleteArticle">
                글삭제
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  width: 100%;
}

.position-relative {
  position: relative;
}

.fixed-top-container {
  position: absolute;
  top: 0;
  right: 0;
  width: 200px;
  z-index: 1000;
}

.image-container {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: flex-end;
  height: 200px;
}


.text-center {
  text-align: center;
}
</style>