import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";

import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { useMenuStore } from "@/stores/menu";

const onlyAuthUser = async (to, from, next) => {
  const memberStore = useMemberStore();
  const { userInfo, isValidToken } = storeToRefs(memberStore);
  const { getUserInfo } = memberStore;

  let token = sessionStorage.getItem("accessToken");

  if (userInfo.value != null && token) {
    await getUserInfo(token);
  }
  if (!isValidToken.value || userInfo.value === null) {
    next({ name: "login" });
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/log",
      name: "log",
      component: () => import("../views/TheMyLog.vue"),
    },
    {
      path: "/board",
      name: "board",
      component: () => import("../views/TheBoardView.vue"),
      redirect: { name: "article-list" },
      children: [
        {
          path: "list",
          name: "article-list",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "view/:articleno",
          name: "article-view",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "write/:contentId",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
          props: true,
        },
        {
          path: "modify/:articleno",
          name: "article-modify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
      ],
    },
    {
      path: "/estations",
      name: "estations",
      component: () => import("@/views/TheElectricChargingStation.vue"),
    },
    {
      path: "/camp",
      name: "camp",
      component: () => import("@/views/TheCapmingView.vue"),
    },
    {
      path: "/map",
      name: "map",
      component: () => import("@/views/TheMapView.vue"),
    },
    {
      path: "/hotplace",
      name: "hotplace",
      component: () => import("@/views/TheHotPlaceView.vue"),
    },
    {
      path: "/plan",
      name: "plan",
      beforeEnter: onlyAuthUser,
      component: () => import("@/views/ThePlanView.vue"),
    },
    {
      path: "/favorite",
      name: "favorite",
      beforeEnter: onlyAuthUser,
      component: () => import("@/views/TheFavorite.vue"),
      redirect: { name: "favoriteList" },
      children: [
        {
          path: "list",
          name: "favoriteList",
          component: () =>
            import("@/components/favorite/favoriteContainer.vue"),
        },
        {
          path: "write",
          name: "writePlan",
          component: () =>
            import("@/components/favorite/plan/favoritePlanWrite.vue"),
        },
      ],
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "join",
          name: "join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "mypage",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "friend",
          name: "friend",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "edit",
          name: "edit",
          beforeEnter: onlyAuthUser,
          component: () => import("@/components/users/UserEdit.vue"),
        },
        // {
        //   path: "modify/:userid",
        //   name: "user-modify",
        //   component: () => import("@/components/users/UserModify.vue"),
        // },
      ],
    },
  ],
});

// router.beforeEach(async (to, from, next) => {
//   const memberStore = useMemberStore();
//   const { userInfo, isValidToken } = storeToRefs(memberStore);
//   const { getUserInfo } = memberStore;

//   let token = sessionStorage.getItem("accessToken");

//   if (userInfo.value != null && token) {
//     await getUserInfo(token);
//   }
//   if (!isValidToken.value || userInfo.value === null) {
//     next();
//   } else {
//     const menuStore = useMenuStore();
//     const { changeMenuState } = menuStore;
//     changeMenuState();
//     next();
//   }
// });

export default router;
