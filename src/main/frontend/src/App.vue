<template>
    <div id="app">
        <div class="bg-white p-2 border-b border-grey-300 mb-8 flex justify-between items-center">
            <router-link to="/" class="flex-none mr-16">
                <img
                        alt="Gouvernement du Grand-Duché de Luxembourg"
                        src="./assets/gov-light-2x.png"
                        class="h-12"
                >
            </router-link>

            <router-link v-if="loggedin === null||!loggedin" to="/login"
                         class="flex flex-none items-center pr-8 no-underline text-black ml-16">
                <div>Login</div>
            </router-link>
            <div v-if="loggedin">
                <router-link
                        to="/carOverview"
                        class="flex flex-none items-center pr-8 no-underline text-black ml-16">
                    <div>{{userName}}</div>
                    <div
                            class="bg-grey-light w-8 h-8 flex items-center justify-center rounded-full ml-4 text-sm"
                    >
                        <svg
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 24 24"
                                fill="none"
                                stroke="currentColor"
                                stroke-width="2"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                                class="w-4 h-4 text-grey-dark"
                        >
                            <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                            <circle cx="12" cy="7" r="4"></circle>
                        </svg>
                    </div>
                </router-link>
                <router-link to="/invoiceOverview">
                    invoices
                </router-link>
                <a @click="logout">logout</a>
            </div>

        </div>
        <div class="container mx-auto px-4">
            <router-view/>

        </div>
        <div class="h-16"></div>

    </div>
</template>
<script>
    export default {
        name: "app",
        data() {
            loggedin:null;
            userName:"";
        },
        props: {},
        methods: {
            checkLogin: function () {
                if (localStorage.getItem('token') !== null) {
                    this.loggedin = true;
                    this.name = localStorage.getItem('token');
                    this.userName = localStorage.getItem('userName')
                }
            },
            logout: function () {
                localStorage.clear();
                this.loggedin = false;
            }
        },
        beforeMount() {
            this.loggedin = false;
            this.checkLogin()
        }
    }
</script>
