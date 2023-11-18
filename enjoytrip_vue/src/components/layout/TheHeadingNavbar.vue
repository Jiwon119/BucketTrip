<script setup>
import { useMenuStore } from "@/stores/menu";
import { useMemberStore } from "@/stores/member";
import { storeToRefs } from "pinia";

const menuStore = useMenuStore();
const memberStore = useMemberStore();

// 반응형을 유지하면서 스토어에서 속성을 추출하려면, storeToRefs()를 사용
// https://pinia.vuejs.kr/core-concepts/
const { menuList } = storeToRefs(menuStore);
const { changeMenuState } = menuStore;
const { userLogout } = memberStore;

const logout = () => {
  console.log("로그아웃!!!!");
  userLogout("ssafy");

  changeMenuState();
};
</script>

<template>
  <div>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
      <div class="container-fluid">
        <router-link :to="{ name: 'main' }" class="navbar-brand" style="color: #7685b5">
          <img src="@/assets/logo1.png" style="width: 200px; margin-left: 30px" />
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor04"
          aria-controls="navbarColor04" aria-expanded="false" aria-label="Toggle navigation"
          style="margin: 0px 30px 0px 30px; color: #7685b5">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor04">
          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link :to="{ name: 'board' }" class="nav-link">게시판</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'estations' }" class="nav-link">전기차충전소</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'camp' }" class="nav-link">camp</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/" class="nav-link">-----</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'bookmark' }" class="nav-link">즐겨찾기</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'map' }" class="nav-link">지도</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{ name: 'hotplace' }" class="nav-link">후기</router-link>
            </li>

            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true"
                aria-expanded="false">Dropdown</a>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Separated link</a>
              </div>
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
</style>
