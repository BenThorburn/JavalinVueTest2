<template id="genre-profile">
    <div>
        <dl v-if="genre">
            <dt>Genre ID</dt>
            <dd>{{genre.id}}</dd>
            <dt>Name</dt>
            <dd>{{genre.name}}</dd>
        </ul>
    </div>
</template>
<script>
    Vue.component("genre-profile", {
        template: "#genre-profile",
        data: () => ({
            genre: null,
        }),
        created() {
            const userId = this.$javalin.pathParams["genre-id"];
            fetch(`/api/genres/${genreId}`)
                .then(res => res.json())
                .then(res => this.genre = res)
                .catch(() => alert("Error while fetching genre"));
        }
    });
</script>