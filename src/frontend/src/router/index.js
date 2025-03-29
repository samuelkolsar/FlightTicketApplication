// router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import FindFlight from '@/views/FindFlight.vue'
import ChooseSeats from '@/views/ChooseSeats.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'find-flight',
            component: FindFlight,
        },
        {

            path: '/choose-seats/:flightId/:selectedClass/:travellers',
            name: 'ChooseSeats',
            component: ChooseSeats,
            props: true
        }
    ]
})

export default router