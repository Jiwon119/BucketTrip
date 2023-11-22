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
    <div class="favoriteCard">
      <div @click="clickPlan(list)">
        <div>제목 : {{ list.title }}</div>
        <div>내용 : {{ list.content }}</div>
        <template v-for="item in list.attrInfo" :key="item.id">
          <div class="div">
            <div v-if="userAttrId.includes(item.contentId)">
              <input type="button" class="btn btn-secondary" @click="clickStamp(item.contentId)">
            </div>
            <div v-else>
              <input type="button" class="btn btn-primary" @click="router.push({
                name: 'article-write',
                params: { contentId: item.contentId }
              })">
            </div>
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