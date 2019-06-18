<template>

    <div class="row">
            <div class="col-md-2"  v-for="car in cars" v-bind:key="car.registrationID">

            <h1>{{ car.brand }}</h1><br />{{car.registrationID}}
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "carOverview",
        data() {
            return {
                cars: {},
                error: '',
                name: ""
            }
        }, created() {
            this.getAllCars();
        },

        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        methods: {
            getAllCars: function () {
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');
                axios.get('/api/car/'+localStorage.getItem('userId')).then(respone => {
                    console.log(respone.data);this.cars = respone.data;
                }).catch(e => {
                    console.log(e);
                });
            }
        }
    }
</script>

<style scoped>
</style>
