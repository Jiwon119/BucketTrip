<script setup>
import { ref } from 'vue';
import UserFriend from './friend/UserFriend.vue';
import FriendRequestAccept from "./friend/FriendRequestAccept.vue";

const friendData = ref({
    name: '친구의 이름',
    profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
    id: '친구의 아이디',
});
</script>

<template>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <img src="https://i.ibb.co/Vm3Hv1C/ssafy-logo.png" class="card-img-top" alt="프로필 이미지" />
                    <div class="card-body">
                        <h5 class="card-title">이름: 사용자 이름</h5>
                        <p class="card-text">생일: 생일 정보</p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">내 정보</h5>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">아이디: 사용자 아이디</li>
                            <li class="list-group-item">이메일: user@example.com</li>
                            <li class="list-group-item">핸드폰번호: 010-1234-5678</li>
                            <li class="list-group-item">가입날짜: 2023년 11월 22일</li>
                            <!-- 기타 사용자 정보 항목을 추가하세요. -->
                        </ul>
                    </div>
                </div>
                <router-link :to="{ name: 'edit' }" class="btn btn-primary">개인 정보 수정</router-link>
            </div>
            <UserFriend :info="friendData" />
            <FriendRequestAccept />
        </div>
    </div>
</template>

<style scoped>
.container {
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
