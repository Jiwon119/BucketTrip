<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList, menuState } = storeToRefs(menuStore);
const { isLogin } = storeToRefs(memberStore);
const { changeMenuState } = menuStore;
const { userLogout } = memberStore;

const logout = () => {
  console.log("로그아웃!!!!");
  userLogout("ssafy");
  changeMenuState();
  menuState.value = false;
};

if (!menuState.value) {
  console.log("------------------isLogin.info", isLogin.info != null);
  if (isLogin.info != null) {
    // changeMenuState();
  }
}
</script>

<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <router-link :to="{ name: 'main' }" class="navbar-brand" style="color: #7685b5">
          <img src="@/assets/logo.png" style="width: 200px; margin-left: 30px" />
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor04"
          aria-controls="navbarColor04" aria-expanded="false" aria-label="Toggle navigation"
          style="margin: 0px 30px 0px 30px; color: #7685b5">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor04">
          <ul class="navbar-nav me-auto">
            <li class="nav-item m-2">
              <router-link :to="{ name: 'map' }" class="nav-link">관광지검색</router-link>
            </li>
            <li class="nav-item m-2">
              <router-link :to="{ name: 'favorite' }" class="nav-link">즐겨찾기</router-link>
            </li>
            <li class="nav-item m-2">
              <router-link :to="{ name: 'plan' }" class="nav-link">버킷리스트</router-link>
            </li>
            <li class="nav-item m-2">
              <router-link :to="{ name: 'hotplace' }" class="nav-link">핫플</router-link>
            </li>
            <li class="nav-item m-2">
              <router-link :to="{ name: 'board' }" class="nav-link">게시판</router-link>
            </li>
            <li class="nav-item m-2">
              <router-link :to="{ name: 'log' }" class="nav-link">여행로그</router-link>
            </li>
          </ul>


          <ul class="navbar-nav me-3">
            <template v-for="menu in menuList" :key="menu.routeName">
              <template v-if="menu.show">
                <template v-if="menu.routeName === 'logout'">
                  <li class="nav-item">
                    <router-link to="/" @click.prevent="logout" class="nav-link">{{
                      menu.name
                    }}</router-link>
                  </li>
                </template>
                <template v-else>
                  <li class="nav-item">
                    <router-link :to="{ name: menu.routeName }" class="nav-link">{{
                      menu.name
                    }}</router-link>
                  </li>
                </template>
              </template>
            </template>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.nav-item * {
  color: #7685b5;
  margin-top: 15px;
}

li {
  font-size: 18px;
}
</style>
