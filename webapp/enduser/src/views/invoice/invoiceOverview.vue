<template>
    <div>
        <div class="row">
            <div class="col-md-2">
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Start Datum</th>
                        <th scope="col">Eind Datum</th>
                        <th scope="col">Bedrag</th>
                        <th scope="col">Betaal</th>
                        <th scope="col">Meer informatie</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="invoice in invoices" v-bind:key="invoices.id">
                        <td> {{formatDate(invoice.startDate)}}</td>
                        <td> {{formatDate(invoice.endDate)}}</td>
                        <td>€ 100,00</td>
                        <td v-if="invoice.payDate == null"> Pay now!!</td>
                        <td v-if="invoice.payDate !=null">Payed on {{formatDate(invoice.payDate)}}</td>
                        <td>Meer info </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>


    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: "invoiceOverview",
        data() {
            return {
                invoices: {},
                error: '',
                name: ""
            }
        }, created() {
            this.getAllInvoices();
        },

        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        methods: {
            
            formatDate(unix) {
                const date = new Date(unix * 1);
                const year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                if (day.toString().length !== 2) day = `0${day}`;
                if (month.toString().length !== 2) month = `0${month}`;

                return `${year}-${month}-${day}`;
            },
            getAllInvoices: function () {
                axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');

                axios.get('/enduser/api/invoice/'+localStorage.getItem('userId')).then(respone => {
                    console.log(respone.data);
                    this.invoices = respone.data;
                }).catch(e => {
                    console.log(e);
                });
            }
        }
    }
</script>

<style scoped>

</style>
