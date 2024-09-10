<template>

    <v-data-table
        :headers="headers"
        :items="getBedsIdByhpid"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'GetBedsIdByhpidView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            getBedsIdByhpid : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/getBedsIdByhpids'))

            temp.data._embedded.getBedsIdByhpids.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.getBedsIdByhpid = temp.data._embedded.getBedsIdByhpids;
        },
        methods: {
        }
    }
</script>

