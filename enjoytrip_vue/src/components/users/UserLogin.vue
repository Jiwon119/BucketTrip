<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

import { searchPass, update } from "@/api/user";

const router = useRouter();

const memberStore = useMemberStore();
const menuStore = useMenuStore();

const { isLogin } = storeToRefs(memberStore);
const { menuState } = storeToRefs(menuStore);
const { userLogin, getUserInfo } = memberStore;
const { changeMenuState } = menuStore;

const loginUser = ref({
    id: "ssafy",
    password: "1234",
});

const login = async () => {
    await userLogin(loginUser.value);
    let token = sessionStorage.getItem("accessToken");
    if (isLogin) {
        getUserInfo(token);
        changeMenuState();
    }

    menuState.value = true;
    router.push("/");
};

const resetPasswordModal = ref(false);
const resetPasswordUser = ref({
    id: "",
    email: "",
    name: "",
    password: "",
});

const openResetPasswordModal = () => {
    resetPasswordModal.value = true;
};

const closeResetPasswordModal = () => {
    resetPasswordModal.value = false;
};

const resetPasswordSubmit = () => {
    searchPass(
        resetPasswordUser.value,
        ({ data }) => {
            console.log(data);
            if (data.msg === "회원정보 일치") {
                resetPasswordUser.value.name = data.member.name;
                update(
                    resetPasswordUser.value,
                    ({ data }) => {
                        console.log(data);
                    },
                    (error) => {
                        console.log(error);
                    }
                );
                alert("비밀번호가 변경되었습니다.");
            }
            else{
                alert("잘못된 회원정보 입니다.");
            }
        },
        (error) => {
            console.log(error);
        }
    );

    // await resetPassword(resetPasswordUser.value);
    closeResetPasswordModal();
};

</script>

<template>
    <div class="main">
        <form v-on:submit.prevent>
            <img class="mb-4" src="@/assets/logo.png" width="200">
            <h1 class="h3 mb-3 fw-normal">로그인</h1>

            <div class="form-floating mb-3">
                <input type="text" class="form-control" placeholder="ID" v-model="loginUser.id">
                <label for="floatingInput">아이디</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" placeholder="Password" v-model="loginUser.password">
                <label for="floatingPassword">비밀번호</label>
            </div>

            <div class="form-check text-start my-3">
                <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <button class="btn w-100 py-2" @click="login">로그인</button>

            <!-- Forget Password 기능 -->
            <div class="mt-3 text-center">
                <a href="#" @click.prevent="openResetPasswordModal">Forget Password?</a>
            </div>

            <p class="mt-5 mb-3 text-body-secondary">&copy; 2017–2023</p>
        </form>

        <!-- Forget Password 모달 -->
        <div v-if="resetPasswordModal" class="modal">
            <div class="modal-content">
                <span class="close" @click="closeResetPasswordModal">&times;</span>
                <h2>Reset Password</h2>
                <form v-on:submit.prevent="resetPasswordSubmit">
                    <label for="resetId">ID:</label>
                    <input type="text" id="resetId" v-model="resetPasswordUser.id" required>
                    <label for="resetEmail">Email:</label>
                    <input type="email" id="resetEmail" v-model="resetPasswordUser.email" required>

                    <!-- 항상 새로운 비밀번호 입력 폼을 보이도록 설정 -->
                    <label for="newPassword">New Password:</label>
                    <input type="password" id="newPassword" v-model="resetPasswordUser.password" required>

                    <button type="submit">Reset Password</button>
                </form>
            </div>
        </div>

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

.modal {
    display: block;
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0,0,0,0.4);
}

.modal-content {
    background-color: #fefefe;
    margin: 15% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>