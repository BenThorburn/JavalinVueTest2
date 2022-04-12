<template id="axios-overview">
  <app-frame>
      <h1> Axios overview </h1>
      <div>
        <button v-on:click="getName()">z</button>
      </div>
      <div>
        <p>{{results}}</p>
      </div>
  </app-frame>
</template>

<script>
    Vue.component("axios-overview", {
        template: "#axios-overview",
        data: () => ({
            results: {},
        }),
          beforeMount(){
            this.getName();
          },
        methods: {
            getName(){
              fetch('https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR');
              const data = await res.text();
              this.data = data;
            }
        }
    });
</script>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>








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