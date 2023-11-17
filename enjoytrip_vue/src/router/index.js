import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "../views/TheMainView.vue";
// import TheBoardView from "../views/TheBoardView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: TheMainView,
    },
    {
      path: "/board",
      name: "board",
      // component: TheBoardView,
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
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
          path: "write",
          name: "article-write",
          component: () => import("@/components/board/BoardWrite.vue"),
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
      component: () => import("@/views/TheElectricChargingStation.vue")
    },
    {
      path: "/camp",
      name: "camp",
      component: () => import("@/views/TheCapmingView.vue")
    },

    {
      path: "/bookmark",
      name: "bookmark",
      component: () => import("@/views/TheUserView.vue")
    },
    {
      path: "/map",
      name: "map",
      component: () => import("@/views/TheUserView.vue")
    },
    {
      path: "/review",
      name: "review",
      component: () => import("@/views/TheUserView.vue")
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
          component: () => import("@/components/users/UserMyPage.vue"),
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

export default router;
