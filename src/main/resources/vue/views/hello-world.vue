<template id="hello-world">
    <header>
        <h1 class="hello-world">Hello, World!</h1>
    </header>
</template>
<script>
    Vue.component("hello-world", {template: "#hello-world"});
</script>
<style>
    .hello-world {
        color: goldenrod;
    }
</style>