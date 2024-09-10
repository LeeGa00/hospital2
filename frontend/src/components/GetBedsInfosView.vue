<template>

    <v-data-table
        :headers="headers"
        :items="getBedsInfos"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetBedsInfosView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getBedsInfos : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getBedsInfos'))

            temp.data._embedded.getBedsInfos.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getBedsInfos = temp.data._embedded.getBedsInfos;
        },
        methods: {
        }
    }
</script>

