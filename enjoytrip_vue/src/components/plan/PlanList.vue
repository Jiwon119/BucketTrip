<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { searchUserBoard, getUserPosts } from "@/api/board"
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";
import VCard from "../common/VCard.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ planList: Array })
const userAttrId = ref([]);
const userPostId = ref([]);

const emit = defineEmits(["onClickPlan"]);

const clickPlan = (val) => {
  console.log(props.planList)
  emit("onClickPlan", props.planList.findIndex(i => i.id == val.id));
}

onMounted(() => {
  setUserPostAttr();
})


const clickStamp = (contentId) => {
  console.log(contentId);
  const index = userAttrId.value.findIndex(id => id === contentId);
  console.log(index);
  const postId = userPostId.value[index];
  console.log(postId);

  router.push({ name: 'article-view', params: { articleno: postId } })
}

const setUserPostAttr = () => {
  getUserPosts(
    userInfo.value.id,
    ({ data }) => {
      console.log("data", data);
      userAttrId.value = []
      userPostId.value = []
      data.forEach(element => {
        userAttrId.value.push(element.destinationId)
        userPostId.value.push(element.articleNo)
      });
      console.log("userAttrId.value", userAttrId.value);
    },
    (error) => {
      console.log(error);
    }
  );
}

</script>

<template>
  <template v-for="list in props.planList" :key="props.planList.id">
    <div class="favoriteCard card mb-3 shadow">
      <div @click="clickPlan(list)">
        <h2 class="card-title">{{ list.title }}</h2>
        <p class="card-text">{{ list.content }}</p>
        <template v-for="item in list.attrInfo" :key="item.id">
          <div class="button">
            <div v-if="userAttrId.includes(item.contentId)">
              <img class="btn" src="@/assets/medal_true.png" @click="clickStamp(item.contentId)">
              <!-- <input type=" button" class="btn btn-secondary""> -->
            </div>
            <div v-else>
              <img class="btn" src="@/assets/medal_false.png" @click="router.push({
                name: 'article-write',
                params: { contentId: item.contentId }
              })">
            </div>
            <VCard :title="item.title" :imgSrc="item.firstImage" :content="item.addr1" @click="onSelect(list)"
              width="180px" />
          </div>
        </template>
      </div>
    </div>
  </template>
</template>

<style scoped>
.favoriteCard {
  border: none;
  position: relative;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
}

.button {
  display: inline-block;

}

.btn {
  width: 80px;
  position: relative;
  left: -50px;
  top: 80px;
  z-index: 1;
}

.shadow {
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.card-title {
  text-align: left;
  font-weight: bold;
  padding: 30px 0px 0px 30px;
}

.card-text {
  text-align: left;
  font-weight: bold;
  padding: 0px 0px 0px 30px;
}
</style>