
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import HospitalHospitalManager from "./components/listers/HospitalHospitalCards"
import HospitalHospitalDetail from "./components/listers/HospitalHospitalDetail"

import BedsBedsManager from "./components/listers/BedsBedsCards"
import BedsBedsDetail from "./components/listers/BedsBedsDetail"

import PatientPatientManager from "./components/listers/PatientPatientCards"
import PatientPatientDetail from "./components/listers/PatientPatientDetail"

import HospitalizationHospitalizationManager from "./components/listers/HospitalizationHospitalizationCards"
import HospitalizationHospitalizationDetail from "./components/listers/HospitalizationHospitalizationDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/hospitals/hospitals',
                name: 'HospitalHospitalManager',
                component: HospitalHospitalManager
            },
            {
                path: '/hospitals/hospitals/:id',
                name: 'HospitalHospitalDetail',
                component: HospitalHospitalDetail
            },

            {
                path: '/beds/beds',
                name: 'BedsBedsManager',
                component: BedsBedsManager
            },
            {
                path: '/beds/beds/:id',
                name: 'BedsBedsDetail',
                component: BedsBedsDetail
            },

            {
                path: '/patients/patients',
                name: 'PatientPatientManager',
                component: PatientPatientManager
            },
            {
                path: '/patients/patients/:id',
                name: 'PatientPatientDetail',
                component: PatientPatientDetail
            },

            {
                path: '/hospitalizations/hospitalizations',
                name: 'HospitalizationHospitalizationManager',
                component: HospitalizationHospitalizationManager
            },
            {
                path: '/hospitalizations/hospitalizations/:id',
                name: 'HospitalizationHospitalizationDetail',
                component: HospitalizationHospitalizationDetail
            },



    ]
})
