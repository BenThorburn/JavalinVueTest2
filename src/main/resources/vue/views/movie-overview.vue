<template id="movie-overview">
    <div>
        <ul class="movie-overview-list">
            <li v-for="movie in movies">
                <a :href="`/movies/${movie.id}`">{{movie.name}} ({{movie.email}})</a>
            </li>
        </ul>
    </div>
</template>
<script>
    Vue.component("movie-overview", {
        template: "#movie-overview",
        data: () => ({
            movies: [],
        }),
        created() {
            fetch("/api/movies")
                .then(res => res.json())
                .then(res => this.movies = res)
                .catch(() => alert("Error while fetching movies"));
        }
    });
</script>
<style>
    ul.movie-overview-list {
        padding: 0;
        list-style: none;
    }
    ul.movie-overview-list a {
        display: block;
        padding: 16px;
        border-bottom: 1px solid #ddd;
    }
    ul.movie-overview-list a:hover {
        background: #00000010;
    }
</style>