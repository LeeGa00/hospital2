<template>

    <v-card outlined>
        <v-card-title>
            ReservationInfo # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="BedsId" v-model="item.bedsId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="HospitalId" v-model="item.hospitalId" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'ReservationInfoViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/reservationInfos/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

