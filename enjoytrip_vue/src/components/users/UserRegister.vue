<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const memberStore = useMemberStore();

const { userRegist } = memberStore;

const registInfo = ref({
    id: "jw",
    password: "1234",
    passwordCk: "1234",
    name: "jw",
    email: "jw@naver.com",
    phone: "01012345678",
    birth: "1999-01-19"
});

const regist = async () => {
    console.log("회원가입 시작 ----");
    await userRegist(registInfo.value);
    router.push("/");
};

</script>

<template>
    <div class="main">
        <form v-on:submit.prevent>
            <img class="mb-4" src="@/assets/logo1.png" width="200">
            <h1 class="h3 mb-3 fw-normal">회원가입</h1>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="ID" v-model="registInfo.id">
                <label for="floatingInput">아이디</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="PW" v-model="registInfo.password">
                <label for="floatingInput">비밀번호</label>
            </div>

            <div class="form-floating mb-3">
                <input type="password" class="form-control" placeholder="PWCk" v-model="registInfo.passwordCk">
                <label for="floatingInput">비밀번호 확인</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Name" v-model="registInfo.name">
                <label for="floatingInput">이름</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Email" v-model="registInfo.email">
                <label for="floatingInput">이메일</label>
            </div>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="Phone" v-model="registInfo.phone">
                <label for="floatingPassword">핸드폰번호</label>
            </div>

            <div class="form-floating mb-3">
                <input type="date" class="form-control" placeholder="birth" v-model="registInfo.birth">
                <label for="floatingPassword">생일</label>
            </div>

            <button class="btn w-100 py-2" @click="regist">회원가입</button>

            <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
        </form>

    </div>
</template>

<style scoped>
.main {
    height: 100%;
    width: 50%;
    max-width: 500px;
    min-width: 300px;


    display: flex;
    justify-content: center;
    align-items: center;

    margin: auto;
    background-color: white;
    border-radius: 30px;
    box-shadow: -10px -10px 15px rgba(212, 212, 212, 0.12),
        10px 10px 15px rgba(186, 186, 186, 0.12);
}

img {
    width: 70%;
    margin: 30px;
}

form {
    width: 80%;
    margin: auto;
}

.btn {
    background-color: #7685b5;
    color: white;
}
</style>