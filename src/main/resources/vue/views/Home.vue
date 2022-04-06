<template id="Home">
    <app-frame>
        <header>Welcome to smovie</header>
        <div>
            <ul class="user-overview-list">
                <li v-for="user in users">
                    <a :href="`/users/${user.id}`">{{user.name}} ({{user.email}})</a>
                </li>
            </ul>
        </div>
        <button class="button-29" role="button">h</button>
        <button class="button-29" role="button">sfds</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
    </app-frame>
</template>
<script>
    Vue.component("Home", {
        template: "#Home",
        data: () => ({
            users: [],
        }),
        created() {
            fetch("/api/users")
                .then(res => res.json())
                .then(res => this.users = res)
                .catch(() => alert("Error while fetching users"));
        }
    });
</script>