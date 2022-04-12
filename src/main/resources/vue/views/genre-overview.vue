<template id="genre-overview">
    <div>
        <ul class="genres-list">
            <li v-for="genre in genres">
                <a :href="`/genres/${genre.id}`">{{genre.id}} ({{genre.name}})</a>
            </li>
        </ul>
    </div>
</template>
<script>
    Vue.component("genre-overview", {
        template: "#genre-overview",
        data: () => ({
            genres: [],
        }),
        created() {
            fetch("/api/genres")
                .then(res => res.json())
                .then(res => this.genres = res)
                .catch(() => alert("Error while fetching genres"));
        }
    });
</script>
<style>
    ul.genres-list {
        padding: 0;
        list-style: none;
    }
    ul.genres-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.genres-list a:hover {
        background: #00000010;
    }
</style>