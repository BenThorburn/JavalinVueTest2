<template id="movie-profile">
    <div>
        <dl v-if="movie">
            <dt>User ID</dt>
            <dd>{{movie.id}}</dd>
            <dt>Name</dt>
            <dd>{{movie.name}}</dd>
            <dt>Email</dt>
            <dd>{{movie.email}}</dd>
            <dt>Birthday</dt>
            <dd>{{movie.movieDetails.dateOfBirth}}</dd>
            <dt>Salary</dt>
            <dd>{{movie.movieDetails.salary}}</dd>
        </ul>
    </div>
</template>
<script>
    Vue.component("movie-profile", {
        template: "#movie-profile",
        data: () => ({
            movie: null,
        }),
        created() {
            const movieId = this.$javalin.pathParams["movie-id"];
            fetch(`/api/movies/${movieId}`)
                .then(res => res.json())
                .then(res => this.movie = res)
                .catch(() => alert("Error while fetching movie"));
        }
    });
</script>