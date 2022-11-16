
import Vue from 'vue';
import DashboardPlugin from './plugins/dashboard-plugin';
import App from './App.vue';
import store from './store';
import router from './routes/router';

Vue.use(DashboardPlugin);

new Vue({
  el: '#app',
  render: h => h(App),
  router,
  store
});
