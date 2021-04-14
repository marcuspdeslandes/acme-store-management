import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    alias: "/stores",
    name: "stores",
    component: () => import("../components/StoresList.vue")
  },
  {
    path: "/stores/:id/:name?",
    name: "store-details",
    component: () => import("../components/Store.vue")
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
