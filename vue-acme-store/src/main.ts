import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import { BootstrapVue } from 'bootstrap-vue'
import moment from "moment";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);
Vue.config.productionTip = false;
Vue.filter('formatDate', function(value: string) {
  if (value) {
    return moment(String(value)).format('DD/MM/YYYY')
  }
});

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
