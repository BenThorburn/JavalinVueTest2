<template id="Home">
    <app-frame>
        <header>Welcome to smovie</header>
        <ul>
            <li v-for="user in users">
                <a :href="`/users/${user.id}`">{{user.name}}</a>
            </li>
        </ul>
        <button class="button-29" role="button"></button>
        <button class="button-29" role="button">{{u}}</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
        <button class="button-29" role="button">Button 29</button>
    </app-frame>
</template>
<script>
    Vue.component("Home", {template: "#Home"});
</script>