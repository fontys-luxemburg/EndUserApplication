<template>
    <div>
        Je hebt een rekening voor de periode van {{formatDate(invoice.startDate)}} tot {{formatDate(invoice.endDate)}}. <br />.
        Dit is een bedrag van {{formatDate(invoice.totalPrice)}}. <br />
        <input type="button" @click="Pay(invoice)" value="Betaal!"/>
    </div>
</template>
<script>
    import axios from "axios";

    export default {
        name: "invoicePay.vue",
        data(){
            invoice:[]
        },
        beforeCreate() {
            this.invoice= JSON.parse(localStorage.getItem('invoice'));
            console.log(localStorage.getItem('invoice'));
            localStorage.removeItem('invoice');
        },
        methods:{
            Pay:function (invoice) {
                axios({
                    method: 'put',
                    url: '/enduser/api/Payment/?user_id='+localStorage.gettItem('userId')+'&year='+this.GetYear(invoice.startDate)+"&month="+this.GetMonth(invoice.startDate),
                    headers: {
                        'content-type': 'application/json;charset=utf-8',
                    }
                })
                    .then(response => {
                        console.log(response);
                        this.$router.push('/invoiceOverview');
                    })


            },
            formatDate(unix){
                const date = new Date(unix * 1);
                const year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                if (day.toString().length !== 2) day = `0${day}`;
                if (month.toString().length !== 2) month = `0${month}`;

                return `${year}-${month}-${day}`;
            },
            GetMonth(unix){
                const date = new Date(unix * 1);
                let month = date.getMonth() + 1;
                if (month.toString().length !== 2) month = `0${month}`;
                return `${month}`;
            },
            GetYear(unix){
                const date = new Date(unix * 1);
                const year = date.getFullYear();


                return `${year}`;
            }
        }
    }
</script>
