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
            console.log("data", data);
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
            <div class="friend-request-info">
                <div class="img-container">
                    <img :src="friend.profilePicture" :alt="'프로필 이미지 - ' + friend.name" class="profile-img" />
                </div>
                <h5>{{ friend.name }}</h5>
                <p>아이디: {{ friend.id }}</p>
            </div>
            <button @click="() => acceptRequest(friend)" class="btn btn-success">수락</button>
        </div>
    </div>
</template>


<style scoped>
.friend-request-accept {
    display: flex;
    overflow-x: auto;
    border: 1px solid #ddd;
    padding: 20px;
    margin: 10px;
    width: 100%;
}

.friend-request-item {
    flex: 0 0 auto;
    box-sizing: border-box;
    margin-right: 15px;
    min-width: 200px;
}

.friend-request-info {
    display: block;
    align-items: center;
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

.btn-success {
    margin-top: 10px;
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
