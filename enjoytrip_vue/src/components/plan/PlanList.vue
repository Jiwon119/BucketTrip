<script setup>
import { ref, onMounted, watch } from "vue";
import { storeToRefs } from "pinia";
import { searchUserBoard, getUserPosts } from "@/api/board"
import { getFriends, getFriendsPost } from "@/api/plan"
import { useMemberStore } from "@/stores/member";
import { useRouter } from "vue-router";
import VCard from "../common/VCard.vue";

const currentIndex = ref(0);

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);

const props = defineProps({ planList: Array })
const userAttrId = ref([]);
const userPostId = ref([]);
const friendsList = ref([]);
const friendsPost = ref({});

const emit = defineEmits(["onClickPlan"]);

const clickPlan = (val) => {

  if (val == 1) {
    currentIndex.value = currentIndex.value + 1;
    if (currentIndex.value >= props.planList.length) {
      currentIndex.value = 0;
    }
  } else {
    currentIndex.value = currentIndex.value - 1;
    if (currentIndex.value < 0) {
      currentIndex.value = props.planList.length - 1;
    }
  }

  emit("onClickPlan", props.planList.findIndex(i => i.id == props.planList[currentIndex.value].id));
}

onMounted(() => {
  setUserPostAttr();

})

watch(
  () => props.planList.value,
  () => {
    props.planList.forEach(async element => {
      getFriendsList(element.id);
      setUserPostAttr();
    })
  },
  { deep: true }
)

watch(
  () => friendsList.value,
  () => {
    console.log("시작?");
    setFriendsPost();
  },
  { deep: true }
)



const clickStamp = (contentId) => {
  console.log(contentId);
  const index = userAttrId.value.findIndex(id => id === contentId);
  console.log(index);
  const postId = userPostId.value[index];
  console.log(postId);

  router.push({ name: 'article-view', params: { articleno: postId, planId: props.planList.id } })
}

const setUserPostAttr = () => {
  getUserPosts(
    userInfo.value.id,
    ({ data }) => {
      userAttrId.value = []
      userPostId.value = []
      data.forEach(element => {
        userAttrId.value.push(element.destinationId)
        userPostId.value.push(element.articleNo)
      });
    },
    (error) => {
      console.log(error);
    }
  );
}

const getFriendsList = (planId) => {
  getFriends(
    planId,
    ({ data }) => {
      friendsList.value.push(data);
    },
    (error) => {
      console.log(error);
    }
  );
}

const setFriendsPost = () => {
  friendsList.value.forEach((friend, index) => {

    friend.forEach(frId => {
      getFriendsPost(
        frId.id,
        ({ data }) => {
          console.log(frId.id);
          console.log("데이터", data);
          console.log(friendsPost.value.hasOwnProperty(frId.id));
          if (!friendsPost.value.hasOwnProperty(frId.id)) {
            friendsPost.value[frId.id] = data;
          }
          console.log(friendsPost.value);
        },
        (error) => {
          console.log(error);
        }
      );
    });
  });
  // props.planList.forEach(planLi => {
  //   console.log("planLi", planLi.attrInfo);
  //   planLi.attrInfo.forEach(att => {
  //     console.log("att", att);
  //     friendsList.value.forEach(friend => {
  //     getFriendsPost(
  //       att, friend,
  //       ({ data }) => {
  //         console.log("-----------------------------", data);
  //       },
  //       (error) => {
  //         console.log(error);
  //       }
  //     );
  //   });
  // });



}

</script>

<template>
  <div id="carouselExampleInterval" class="carousel carousel-dark" data-bs-ride="false">
    <div class="carousel-indicators">
      <template v-for="(list, index) in props.planList" :key="props.planList.id">
        <button type="button" data-bs-target="#carouselExampleDark" :data-bs-slide-to="index" class="active"></button>
      </template>
    </div>
    <div class="carousel-inner">
      <template v-for="(list, index) in props.planList" :key="props.planList.id">
        <div class="carousel-item p-5" :class="{ 'active': index == 0 }" data-bs-interval="false">
          <div class="d-block w-100 ">
            <h2 class="mt-5"> {{ list.title }}</h2>
            <h3>{{ list.content }}</h3>
            <div>
              <div v-for="friend in friendsList[index]" :key="friend.id" class="friend-profile">
                <div class="img-container">
                  <img :src="friend.profilePicture" class="profile-img" alt="프로필 이미지" />
                </div>
                <h5>{{ friend.id }}<br> ({{ friend.name }})</h5>
                <p></p>
              </div>
            </div>
            <template v-for="item in list.attrInfo" :key="item.id">
              <div class="p-0 button">

                <template v-if="userAttrId.includes(item.contentId)">
                  <img class="btn" src="@/assets/medal_true.png" @click="clickStamp(item.contentId)">
                </template>
                <template v-else>
                  <img class="btn" src="@/assets/medal_false.png" @click="router.push({
                    name: 'article-write',
                    params: { contentId: item.contentId }
                  })">
                </template>
                <div class="row">
                  <div class="col">
                    <VCard :title="item.title" :imgSrc="item.firstImage" :content="item.addr1" width="180px" />
                  </div>
                </div>
                <div class="row text-center">
                  <div class="position-relative">
                    <template v-for="fr in friendsList[index]" :key="fr.id">
                      <template v-if="friendsPost[fr.id].findIndex(e => e.destinationId === item.contentId) != -1">
                        <a class="friendBoard m-1" :href="'/board/view/' + friendsPost[fr.id][friendsPost[fr.id].findIndex(e => e.destinationId ===
                          item.contentId)].articleNo" style="width: min-content; text-align:center;">{{ fr.id }}</a>
                      </template>
                      <template v-else>
                        <a class="friendBoard m-1" style="color:black;pointer-events: none; cursor: default; ">{{ fr.id
                        }}</a>
                      </template>
                    </template>
                  </div>
                </div>
              </div>
            </template>
          </div>
        </div>
      </template>
    </div>
    <button class="carousel-control-prev carousel-dark" type="button" data-bs-target="#carouselExampleInterval"
      data-bs-slide="prev" @click="clickPlan(-1)">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next"
      @click="clickPlan(1)">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</template>

<style scoped>
.carousel-item {
  border: none;
  position: relative;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
}

.favoriteCard {
  border: none;
  position: relative;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  margin: 10px;
  padding: 10px;
}

.friendBoard {
  text-decoration: none;
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
  padding: 10px;
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

.friend-profile {
  display: inline-block;
  flex-direction: column;
  align-items: center;
  margin: 10px;
}
</style>