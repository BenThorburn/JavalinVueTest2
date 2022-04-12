<template id="title-overview">
    <app-frame>
        <p>Hello titles page</p>
        <p v-if="likes > -1"> Likes: {{ likes }} </p>
        <button v-on:click="like()">
            Add like
        </button>

        <button class="btn btn-sm btn-primary" @click="getAllData">Get All</button>
        <div v-for="result in getResult" class="alert alert-secondary mt-2" role="alert">
            <pre>{{getResult}}</pre>
        </div>
    </app-frame>
</template>

<script>
    const baseURL = "https://jsonplaceholder.typicode.com";
    Vue.component("title-overview", {
        template: "#title-overview",
        data: () => ({
            likes: 0,
            getResult: null,
        }),
        methods: {
           like() {
              this.likes++;
           },
            formatResponse(res) {
              return JSON.stringify(res, null, 2);
            },
            async getAllData() {
              try {
                const res = await fetch(`${baseURL}/todos`);
                if (!res.ok) {
                  const message = `An error has occurred: ${res.status} - ${res.statusText}`;
                  throw new Error(message);
                }
                const data = await res.text();
                const result = {
                  data: data,
                };
                this.getResult = this.formatResponse(result);
              } catch (err) {
                this.getResult = err.message;
              }
            },
        }
    });
</script>
















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