<template>
    <div class="max-w-sm mx-auto">
        <div class="bg-white rounded p-6 mb-8">
            <h1 class="text-center mb-8">Login</h1>
            <form role="form" @submit="login">
                <div class="mb-4">
                    <label class="block mb-2">Email</label>
                    <input v-model="email" placeholder="email@email.example" class="bg-grey-lightest p-4 block rounded w-full">
                </div>
                <div class="mb-8">
                    <label class="block mb-2">Password</label>
                    <input v-model="password" placeholder="password" type=password class="bg-grey-lightest p-4 block rounded w-full">                </div>
                <button :disabled="loggingIn" class="w-full bg-blue p-4 rounded text-white">Log in</button>
            </form>
        </div>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: 'login',
        data() {
            return {
                email: '',
                password: '',
                token: '',
                user: {},
                username: '',
                errors: []
            }
        },
        mounted() {
        },
        methods: {
            login: function (e) {
                const bodyFormData = {
                    email: this.email,
                    password: this.password
                }
                var qs = require('qs');
                axios({
                    method: 'post',
                    url: '/enduser/api/authentication',
                    data: JSON.parse(JSON.stringify(bodyFormData)),
                    headers: {
                        'content-type': 'application/json;charset=utf-8',
                    }
                })
                    .then(response => {
                        this.token = response.data.token;
                        localStorage.setItem('token', this.token);
                        localStorage.setItem('userName', response.data.name);
                        localStorage.setItem('userId', response.data.id);
                        localStorage.setItem('userMail', response.data.email);
                        axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
                        this.message= this.token;
                        this.$router.push('/');
                    })
                    .catch(e => {
                        localStorage.clear();
                        this.errors.push(e)
                        alert(e);
                    });
                e.preventDefault();
            }
        }
    };

</script>

<style scoped>
</style>
