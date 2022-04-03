<template id="Selector">
    <div class="Selector">
        <header>
            <span>Selectortemp component</span>
        </header>
        <slot></slot>
    </div>
</template>
<script>
    Vue.component("Selector", {template: "#Selector"});
</script>
<style>
    .Selector > button {
        width: 20px;
        height: 20px;
        padding: 10px;
        background: #b6e2ff;
        font-size: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
</style>