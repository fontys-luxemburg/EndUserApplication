import login from "./components/login";
import Router from "vue-router";
import Vue from "vue";


Vue.use(Router);

export const router = new Router({
    routes: [
        {

            path: '/login',
            name: 'login',
            component: login
        }
    ]
});
