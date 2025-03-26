// router/index.js
import { createRouter, createWebHistory } from "vue-router";
import SearchFlights from "../views/SearchFlights.vue";
import SelectSeats from "../views/SelectSeats.vue";

const routes = [
    { path: "/", component: SearchFlights },
    { path: "/select-seats/:flightId", component: SelectSeats },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
