<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { update } from "@/api/user";

import axios from 'axios';

const router = useRouter();
const memberStore = useMemberStore();
const { userInfo } = storeToRefs(memberStore);
const isFileInputVisible = ref(false);

const userProfile = ref({
    id: userInfo.value.id,
    name: userInfo.value.name,
    profilePicture: userInfo.value.profilePicture,
    birth: userInfo.value.birth.slice(0, 10),
    email: userInfo.value.email,
    phone: userInfo.value.phone,
    joinDate: userInfo.value.joinDate,
});

const handleProfilePictureChange = (event) => {
    const file = event.target.files[0];
    console.log(file);
    if (file) {
        var bodyData = new FormData();
        var imageFile = file;
        bodyData.append("image", imageFile);

        axios.post(`https://api.imgbb.com/1/upload?key=${import.meta.env.VITE_IMGBB_API_KEY}`, bodyData)
            .then(res => {
                const url = res.data.data.display_url;
                console.log(url);
                userProfile.value.profilePicture = url
            })
            .catch((error) => {
                console.log(error);
            })
    }
};

const submitForm = () => {
    console.log("유저 정보 수정");
    console.log(userProfile.value);
    // API 호출
    update(
        userProfile.value,
        ({ data }) => {
            console.log(data);
        },
        (error) => {
            console.log(error);
        }
    );
};

const showFileInput = () => {
    isFileInputVisible.value = true;
};
</script>

<template>
    <div>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
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

                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-body">
                            <h6 class="card-title">내 정보 수정</h6>
                            <form @submit.prevent="submitForm">
                                <div class="mb-3">
                                    <label class="form-label">아이디</label>
                                    <span>{{ userProfile.id }}</span>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">이메일</label>
                                    <span>{{ userProfile.email }}</span>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">가입날짜</label>
                                    <span>{{ userProfile.joinDate }}</span>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label">이름</label>
                                    <input v-model="userProfile.name" type="text" class="form-control" id="name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="phoneNumber" class="form-label">핸드폰 번호</label>
                                    <input v-model="userProfile.phone" type="tel" class="form-control" id="phoneNumber"
                                        required>
                                </div>
                                <div class="mb-3">
                                    <label for="birthday" class="form-label">생일</label>
                                    <input v-model="userProfile.birth" type="date" class="form-control" id="birthday"
                                        required>
                                </div>
                                <button type="submit" class="btn btn-outline-secondary">저장</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
  
<style scoped>
.container {
    text-align: left;
    color: #2c3e50;
    margin-top: 60px;
}


.card-title {
    font-weight: bold;
    font-size: 22px;
    margin-bottom: 20px;
    text-align: center;
}

.form-label {
    font-weight: bold;
    margin-right: 10px;
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

.profile-img-label {
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
    background: rgba(0, 0, 0, 0.5);
    /* 원하는 배경 색상 및 투명도로 설정 */
    display: flex;
    justify-content: center;
    align-items: center;
    color: #fff;
    opacity: 0;
    /* 초기에는 숨겨진 상태 */
    transition: opacity 0.3s ease;
}

.profile-img-label:hover .overlay {
    opacity: 1;
    /* 호버 상태에서 나타나도록 함 */
}
</style>
  