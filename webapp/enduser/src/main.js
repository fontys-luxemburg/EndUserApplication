import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import '@/assets/css/tailwind.css'
import routes from './routes';
Vue.config.productionTip = false
Vue.use(VueRouter);

new Vue({
  routes,
  render: h => h(App)
}).$mount('#app')
