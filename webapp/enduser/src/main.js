import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import '@/assets/css/tailwind.css'
import {router} from './routes';
import store from './store';

Vue.config.productionTip = false
Vue.use(VueRouter);

new Vue({
  router,store,
  render: h => h(App)
}).$mount('#app')
