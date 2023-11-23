<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { getFriendRequest, acceptFriendRequest } from "@/api/user";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const friendRequests = ref([]);

const emit = defineEmits(["onAcceptRequest"]);

const onAcceptRequest = () => {
    emit("onAcceptRequest");
};

onMounted(() => {
    setFriendRequest();
})

const setFriendRequest = () => {
    getFriendRequest(
        userInfo.value.id,
        ({ data }) => {
            console.log("getFriendRequest", data);
            friendRequests.value = data;
        }, (error) => {
            console.error("Error fetching isFavorite:", error);
        });
};

const acceptRequest = (friend) => {
    console.log(friend);
    acceptFriendRequest(
        userInfo.value.id, friend.id,
        ({ data }) => {
            console.log("data", data);
            friendRequests.value = data;
            onAcceptRequest();
            setFriendRequest();
        }, (error) => {
            console.error("Error fetching isFavorite:", error);
        });
};
</script>

<template>
    <div class="friend-request-accept">
        <div v-for="friend in friendRequests" :key="friend.id" class="friend-request-item">
            <label for="acceptRequest" class="accept-equest-label">
                <div class="friend-request-info">
                    <div class="img-container">
                        <img :src="friend.profilePicture" :alt="'프로필 이미지 - ' + friend.name" class="profile-img" />
                    </div>
                    <div class="overlay">
                        <span>수락</span>
                    </div>
                    <h5>{{ friend.name }}</h5>
                    <p>아이디: {{ friend.id }}</p>
                </div>
                <button id="acceptRequest" @click="() => acceptRequest(friend)" class="btn btn-outline-secondary"
                    style="display: none;">수락</button>
            </label>
        </div>
    </div>
</template>

<!-- 
    <label for="profilePicture" class="profile-img-label">
    <div class="img-container">
        <img :src="userProfile.profilePicture" class="profile-img" alt="프로필 이미지" />
    </div>
    <div class="overlay">
        <span>프로필 사진 수정</span>
    </div>
</label>
<div class="card-body">
    <h5 class="card-title">이름: {{ userProfile.name }}</h5>
</div>
<input class="form-control" type="file" id="profilePicture" @change="handleProfilePictureChange"
    accept="image/*" style="display: none;"> 
-->

<style scoped>
.friend-request-accept {
    min-height: 100px;
    display: flex;
    overflow-x: auto;
    border: 1px solid #ddd;
    padding: 10px;
    margin: 10px;
    width: 100%;
}

.friend-request-item {
    flex: 0 0 auto;
    box-sizing: border-box;
    margin-right: 15px;
}

.friend-request-info {
    display: block;
    align-items: center;
    padding: 0 20px;
}

.friend-request-picture {
    width: 70px;
    height: 70px;
    border-radius: 50%;
    border: rgb(203, 203, 203) solid 1px;
    margin-right: 10px;
}

.friend-request-details {
    display: flex;
    flex-direction: column;
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

.accept-equest-label {
    display: inline-block;
    position: relative;
    cursor: pointer;
}

.overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.3);
    /* 원하는 배경 색상 및 투명도로 설정 */
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    opacity: 0;
    /* 초기에는 숨겨진 상태 */
    transition: opacity 0.3s ease;
}

.accept-equest-label:hover .overlay {
    opacity: 1;
    /* 호버 상태에서 나타나도록 함 */
}
</style>
