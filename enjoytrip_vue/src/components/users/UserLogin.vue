<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

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
    console.log("login ing!!!! !!!");
    await userLogin(loginUser.value);
    let token = sessionStorage.getItem("accessToken");
    console.log("111. ", token);
    console.log("isLogin: ", isLogin);
    if (isLogin) {
        console.log("로그인 성공아닌가???");
        getUserInfo(token);
        changeMenuState();
    }

    menuState.value = true;
    router.push("/");
};

</script>

<template>
    <div class="main">
        <form v-on:submit.prevent>
            <img class="mb-4" src="@/assets/logo1.png" width="200">
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