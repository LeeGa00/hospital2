 /*eslint-disable*/
import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import Managing from "./components";
import router from './router';
Vue.config.productionTip = false;
require('./GlobalStyle.css');

const axios = require("axios").default;

// backend host url
axios.backend = "https://8088-leega00-hospital2-b0c1bbqyh2p.ws-us116.gitpod.io";

try {
  // URL 생성 시 오류가 발생할 수 있으므로 예외 처리
  axios.backendUrl = new URL(axios.backend);
} catch (e) {
  console.error("Invalid backend URL:", e.message);
}

axios.fixUrl = function(original) {
  if (!axios.backend && original.indexOf("/") === 0) return original;

  var url = null;

  try {
    url = new URL(original);
  } catch (e) {
    // backend URL과 결합하여 새로운 URL 생성
    url = new URL(axios.backend + original);
  }

  if (!axios.backend) return url.pathname;

  // axios.backendUrl이 제대로 정의되었는지 확인
  if (axios.backendUrl) {
    url.hostname = axios.backendUrl.hostname;
    url.port = axios.backendUrl.port;
  } else {
    console.error("axios.backendUrl is not defined.");
  }

  return url.href;
};

/*
// backend host url
axios.backend = "http://localhost:8088";

// axios.backendUrl = new URL(axios.backend);
axios.fixUrl = function(original){

  if(!axios.backend && original.indexOf("/")==0) return original;

  var url = null;

  try{
    url = new URL(original);
  }catch(e){
    url = new URL(axios.backend + original);
  }

  if(!axios.backend) return url.pathname;

  url.hostname = axios.backendUrl.hostname;
  url.port = axios.backendUrl.port;

  return url.href;
}
  */

const templateFiles = require.context("./components", true);
Vue.prototype.$ManagerLists = [];
templateFiles.keys().forEach(function(tempFiles) {
  if (!tempFiles.includes("Manager.vue") && tempFiles.includes("vue")) {
    Vue.prototype.$ManagerLists.push(
      tempFiles.replace("./", "").replace(".vue", "")
    );
  }
});
Vue.use(Managing);
const pluralCaseList = []

pluralCaseList.push( {plural: "hospitals/hospitals", pascal: "HospitalHospital"} )

pluralCaseList.push( {plural: "beds/beds", pascal: "BedsBeds"} )

pluralCaseList.push( {plural: "patients/patients", pascal: "PatientPatient"} )

pluralCaseList.push( {plural: "hospitalizations/hospitalizations", pascal: "HospitalizationHospitalization"} )


Vue.prototype.$ManagerLists.forEach(function(item, idx) {
  pluralCaseList.forEach(function(tmp) {
    if(item.toLowerCase() == tmp.pascal.toLowerCase()) {
      var obj = {
        name: item,
        plural: tmp.plural
      }
      Vue.prototype.$ManagerLists[idx] = obj
    }
  })
})

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount("#app");
