import login from "./components/login";
import Router from "vue-router";
import Vue from "vue";
import carOverview from "./views/car/carOverview";
import invoiceOverview from "./views/invoice/invoiceOverview";
import invoiceDetails from "./views/invoice/invoiceDetails";


Vue.use(Router);

export const router = new Router({
    routes: [
        {

            path: '/login',
            name: 'login',
            component: login
        },
        {
            path: '/carOverview',
            name:'carOverview',
            component: carOverview
        },
        {
            path:"/invoiceOverview",
            name:"invoiceOverview",
            component:invoiceOverview
        },
        {
            path:'/invoiceDetails/',
            name:'invoiceDetails',
            component:invoiceDetails
        }
    ]
});
