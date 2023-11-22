<!-- src/views/UserEdit.vue -->
<template>
    <div>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <img :src="userProfile.profilePicture" class="card-img-top" alt="프로필 이미지" />
                        <div class="card-body">
                            <h5 class="card-title">이름: {{ userProfile.name }}</h5>
                        </div>
                        <div class="mt-3">
                            <label for="profilePicture" class="form-label">프로필 사진 수정</label>
                            <input type="file" id="profilePicture" @change="handleProfilePictureChange" accept="image/*">
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">내 정보 수정</h5>
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
                                    <input v-model="userProfile.phoneNumber" type="tel" class="form-control"
                                        id="phoneNumber" required>
                                </div>
                                <div class="mb-3" v-if="editableFields.birthday">
                                    <label for="birthday" class="form-label">생일</label>
                                    <input v-model="userProfile.birthday" type="text" class="form-control" id="birthday"
                                        required>
                                </div>
                                <div class="mb-3" v-else>
                                    <label class="form-label">생일</label>
                                    <span>{{ userProfile.birthday }}</span>
                                </div>
                                <button type="submit" class="btn btn-primary">저장</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const userProfile = ref({
    id: '사용자 아이디',
    name: '사용자 이름',
    profilePicture: 'https://i.ibb.co/Vm3Hv1C/ssafy-logo.png',
    birthday: '생일 정보',
    email: 'user@example.com',
    phoneNumber: '010-1234-5678',
    joinDate: '2023년 11월 22일',
    // 추가 정보들...
});

const editableFields = {
    birthday: true,
    phoneNumber: true,
};

const handleProfilePictureChange = (event) => {
    const file = event.target.files[0];
    if (file) {
        userProfile.profilePicture = URL.createObjectURL(file);
    }
};

const submitForm = () => {
    // 여기에서 서버에 수정된 정보를 전송하거나, 로컬 상태를 업데이트하는 등의 작업 수행
    router.push('/');
};
</script>
  
<style scoped>
.container {
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}

.form-label {
    font-weight: bold;
}
</style>
  