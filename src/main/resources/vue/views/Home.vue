<template id="Home">
    <app-frame>
        <header>Welcome to smovie</header>
        <div id="app">
          <p>Search for: {{ searchPH }}</p>
          <p><input v-model="searchPH"></p>
        </div>
        <div>
            <ul class="user-overview-list">
                <li v-for="movie in movies">
                    <a :href="`/movies/${movie.id}`">{{movie.name}} ({{movie.email}})</a>
                </li>
            </ul>
        </div>
        <button class="button-29" role="button" @click="show()">trigger</button>
        <span>{{message}}</span>
        <button class="button-29" role="button">sfds</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <span id ="hello">Method span text</p>
    </app-frame>
</template>

<script>
    Vue.component("Home", {
        template: "#Home",
        data: () => ({
            movies: [],
            message: 'dcbdsc',
            searchPH: 'Toy Story'
        }),
        created() {
            fetch("/api/movies")
                .then(res => res.json())
                .then(res => this.movies = res)
                .catch(() => alert("Error while fetching users"));
        },
        methods : {
            // Creating function
            show: function(){
                // Setting text in element
                document.getElementById("hello").innerHTML = "Hi, This is Vue"
            }
        },
    });
</script>

