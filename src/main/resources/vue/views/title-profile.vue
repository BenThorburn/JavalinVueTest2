<template id="title-profile">
    <div>
        <dl v-if="title">
            <dt>Title Name</dt>
            <dd>{{title}}</dd>
        </ul>
    </div>
</template>
<script>
    Vue.component("title-profile", {
        template: "#title-profile",
        data: () => ({
            title: null,
        }),
        created() {
            const titleName = this.$javalin.pathParams["title-name"];
            fetch(`/api/titles/${titleName}`)
                .then(res => res.json())
                .then(res => this.title = res)
                .catch(() => alert("Error while fetching" + titleName));
        }
    });
</script>