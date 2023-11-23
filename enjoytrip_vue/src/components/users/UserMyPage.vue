<script setup>
import { ref, onMounted } from 'vue';
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import UserFriend from './friend/UserFriend.vue';
import FriendRequestAccept from "./friend/FriendRequestAccept.vue";

const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const refreshAlert = ref(true);
const refreshFriendList = () => {
    console.log(refreshAlert.value);
    refreshAlert.value = !refreshAlert.value
    console.log(refreshAlert.value);

}

const userProfile = ref({})

onMounted(() => {
    userProfile.value = {
        id: userInfo.value.id,
        name: userInfo.value.name,
        profilePicture: userInfo.value.profilePicture,
        birth: userInfo.value.birth.slice(0, 10),
        email: userInfo.value.email,
        phone: userInfo.value.phone,
        joinDate: userInfo.value.joinDate.slice(0, 10),
    };
})

</script>

<template>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="img-container">
                        <img :src="userProfile.profilePicture" class="profile-img" alt="프로필 이미지" />
                    </div>
                    <div class="card-body">
                        <h5 class="card-title">이름: {{ userProfile.name }}</h5>
                        <p class="card-text">생일: {{ userProfile.birth }}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">내 정보</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">아이디: {{ userProfile.id }}</li>
                            <li class="list-group-item">이메일: {{ userProfile.email }}</li>
                            <li class="list-group-item">핸드폰번호: {{ userProfile.phone }}</li>
                            <li class="list-group-item">가입날짜: {{ userProfile.joinDate }}</li>
                            <!-- 기타 사용자 정보 항목을 추가하세요. -->
                        </ul>
                    </div>
                </div>
                <router-link :to="{ name: 'edit' }" class="btn btn-outline-secondary m-3">개인 정보 수정</router-link>
            </div>
            <h5 class="title">친구 목록</h5>
            <UserFriend :info="friendData" :refreshAlert="refreshAlert" />
            <h5 class="title">친구 요청</h5>
            <FriendRequestAccept @on-accept-request="refreshFriendList" />
        </div>
    </div>
</template>

<style scoped>
.container {
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}

.title {
    font-weight: bold;
    margin-top: 30px;
    margin-left: 10px;
    text-align: left;
}

.img-container {
    width: 90%;
    height: 0;
    padding-top: 90%;
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
