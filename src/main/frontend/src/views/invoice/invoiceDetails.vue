<template>
    <div>

        {{invoice.endDate}}
    </div>
</template>

<script>
    export default {
        name: "invoiceDetails.vue",
        data(){
            invoice:[]
        },
        beforeCreate() {
            this.invoice= JSON.parse(localStorage.getItem('invoice'));
            console.log(localStorage.getItem('invoice'))
            localStorage.removeItem('invoice');
        }
    }
</script>

<style scoped>

</style>
