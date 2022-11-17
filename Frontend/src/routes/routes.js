import DashboardLayout from "@/views/Layout/DashboardLayout.vue";
import AuthLayout from "@/views/Pages/AuthLayout.vue";

import NotFound from "@/views/NotFoundPage.vue";

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    redirect: "dashboard",
    component: DashboardLayout,
    children: [
      {
        path: "/dashboard",
        name: "dashboard",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Dashboard.vue"),
      },
      {
        path: "/icons",
        name: "icons",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Icons.vue"),
      },
      {
        path: "/profile",
        name: "profile",
        component: () =>
          import(
            /* webpackChunkName: "demo" */ "../views/Pages/UserProfile.vue"
          ),
      },
      {
        path: "/maps",
        name: "maps",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/GoogleMaps.vue"),
      },
      {
        path: "tables",
        name: "tables",
        component: () => import("../views/RegularTables"),
        children: [
          {
            path: "",
            name: "boardlist",
            component: () =>
              import(`../views/Tables/RegularTables/LightTable.vue`),
          },
          {
            path: "view",
            name: "boardview",
            component: () =>
              import("../views/Tables/RegularTables/LightTableView.vue"),
          },
        ],
      },
    ],
  },
  {
    path: "/",
    redirect: "login",
    component: AuthLayout,
    children: [
      {
        path: "/login",
        name: "login",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/Login.vue"),
      },
      {
        path: "/register",
        name: "register",
        component: () =>
          import(/* webpackChunkName: "demo" */ "../views/Pages/Register.vue"),
      },
      { path: "*", component: NotFound },
    ],
  },
];

export default routes;
