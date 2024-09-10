<template>
  <div>
    <!-- Beds Title Card -->
    <v-card
      class="mx-auto"
      outlined
      color="primary"
      style="padding:10px 0px 10px 0px; margin-bottom:40px;"
    >
      <v-row>
        <v-list-item class="d-flex">
          <v-list-item-avatar
            size="70"
            class="border-color"
          >
          </v-list-item-avatar>
          <h1 class="align-self-center ml-3">Beds</h1>
          <div class="secondary-text-color" style="margin-left:30px;"></div>
        </v-list-item>
      </v-row>
    </v-card>

    <!-- Beds 등록 Button and Dialog -->
    <v-col style="margin-bottom:40px;">
      <div class="text-center">
        <v-dialog
          v-model="openDialog"
          width="332.5"
          fullscreen
          hide-overlay
          transition="dialog-bottom-transition"
        >
          <BedsBeds
            :offline="offline"
            class="video-card"
            :isNew="true"
            :editMode="true"
            v-model="newValue"
            @add="append"
            v-if="tick"
          />
          <v-btn
            style="position:absolute; top:2%; right:2%"
            @click="closeDialog"
            depressed
            icon
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-dialog>

        <v-row>
          <v-card
            class="mx-auto"
            style="height:300px; width:300px; margin-bottom:20px; text-align: center;"
            outlined
          >
            <v-list-item>
              <v-list-item-avatar 
                class="mx-auto"
                size="80"
                style="margin-top:80px;"
              >
                <v-icon color="primary" x-large>mdi-plus</v-icon>
              </v-list-item-avatar>
            </v-list-item>

            <v-card-actions>
              <v-btn 
                class="mx-auto"
                outlined
                rounded
                @click="openDialog=true"
                color="primary"
                style="font-weight:500; font-size:20px; padding:15px; border:solid 2px; max-width:250px; overflow:hidden"
              >
                Beds 등록
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-row>
      </div>
    </v-col>

    <!-- Render the Beds -->
    <v-row>
      <v-col
        v-for="(bed, index) in values"
        :key="bed.hpid"
        cols="12"
        md="4"
      >
        <v-card outlined class="mx-auto" max-width="400">
          <v-card-title>
            <span class="headline">{{ bed.hospitalName }}</span>
          </v-card-title>
          <v-card-text>
            <div><strong>위도:</strong> {{ bed.lat }}</div>
            <div><strong>경도:</strong> {{ bed.lng }}</div>
            <div><strong>총 병상 수:</strong> {{ bed.totalBeds }}</div>
            <div><strong>남은 병상 수:</strong> {{ bed.remain }}</div>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="primary" @click="viewDetails(bed)">
              자세히 보기
            </v-btn>
            <v-btn text color="red" @click="remove(bed)">
              삭제
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
const axios = require('axios').default;
import BedsBeds from './../BedsBeds.vue';

export default {
  name: 'BedsBedsManager',
  components: {
    BedsBeds,
  },
  props: {
    offline: Boolean
  },
  data: () => ({
    values: [],
    newValue: {
      hospitalName: '',
      lat: 0,
      lng: 0,
      remain: 0,
      totalBeds: 0,
      hpid: '',
    },
    tick: true,
    openDialog: false,
  }),
  async created() {
    var me = this;
    if (me.offline) {
      if (!me.values) me.values = [];
      return;
    }

    try {
      var res = await axios.get(axios.fixUrl('/beds'));
      me.values = res.data._embedded.beds;
    } catch (error) {
      console.error('Error fetching beds:', error);
    }
  },
  methods: {
    on(event) {
      console.log('Button clicked!', event);
      this.openDialog = true; // 다이얼로그 열기
    },
    closeDialog() {
      this.openDialog = false;
    },
    append(value) {
      this.tick = false;
      this.newValue = {};
      this.values.push(value);

      this.$emit('input', this.values);

      this.$nextTick(function () {
        this.tick = true;
      });
    },
    remove(value) {
      this.values = this.values.filter((bed) => bed._links.self.href !== value._links.self.href);
      this.$emit('input', this.values);
    },
    viewDetails(bed) {
      console.log('View details:', bed);
    }
  }
};
</script>

<style>
.video-card {
  width: 300px;
  margin-left: 4.5%;
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>
