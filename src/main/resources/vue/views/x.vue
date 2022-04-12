<template id="x">
    <div>
        <ul class="user-overview-list">
            <li v-for="title in titles">
                <a>{{title}}</a>
            </li>
        </ul>
    </div>
</template>
<script>
    Vue.component("x", {
        template: "#x",
        data: () => ({
            titles: [],
        }),
        created() {
            fetch("/api/titles")
                .then(res => res.json())
                .then(res => this.titles = res.titles)
                .catch(() => alert("Error while fetching titles"));
        }
    });
</script>
<style>
    ul.user-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.user-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.user-overview-list a:hover {
        background: #00000010;
    }
</style>