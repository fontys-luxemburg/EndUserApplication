import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router';
import '@/assets/css/tailwind.css'
import {router} from './routes';
import VueMoment from 'vue-moment'
import moment from 'moment-timezone'

Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(VueMoment, {
    moment,
})
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
