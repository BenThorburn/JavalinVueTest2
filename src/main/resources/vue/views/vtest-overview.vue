<template id="vtest-overview">
    <section>
        <p>Hello titles page</p>
        <pre>{{ fetchResponse }}</pre>
        <button @click="loadFetch()">Load Companies (Fetch)</button>



        <!--
        <div v-for="title in titles">
            {{titles}}
        </div>
        -->


    </section>
</template>

<script>
    Vue.component("vtest-overview", {


        template: "#vtest-overview",


        data: () => ({
            result: null,
            fetchResponse: {},
            fetchError: ''
        }),


        methods: {
            loadFetch() {
                fetch( 'https://api.roastandbrew.coffee/api/v1/companies' )
                    .then( function( response ){
                        if( response.status != 200 ){
                            throw response.status;
                        }else{
                            return response.json();
                        }
                    }.bind(this))
                    .then( function( data ){
                        this.fetchResponse = data;
                    }.bind(this))
                    .catch( function( error ){
                        this.fetchError = error;
                    }.bind(this));
            }
        }
    });
</script>









<!--

<script>
    Vue.component("vtest-overview", {
        template: "#vtest-overview",
        data: () => ({
            titles: [],
        }),
        created() {
            fetch("/api/titles")
                .then(res => res.text())
                .then(res => this.titles = res)
                .catch(() => alert("Error while fetching titles"));
        }
    });
</script>


-->







<!--
<script>
    const url = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR";
    Vue.component("vtest-overview", {
        template: "#vtest-overview",
        data: {
          results: []
        },
        mounted() {
          axios.get(url).then(response => {
            this.results = response.data
          })
        }
    });
</script>
-->









<!--
<style>
    ul.title-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.title-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.title-overview-list a:hover {
        background: #00000010;
    }
</style>
-->