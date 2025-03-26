<script setup>
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
const flightId = ref(route.params.flightId);
const seats = ref([]);
const selectedSeats = ref([]);

const fetchSeats = async () => {
  try {
    const response = await fetch(`http://localhost:8080/api/seats/${flightId.value}`);
    seats.value = await response.json();
  } catch (error) {
    console.error("Error fetching seats:", error);
  }
};

const toggleSeatSelection = (seat) => {
  if (selectedSeats.value.includes(seat.id)) {
    selectedSeats.value = selectedSeats.value.filter((id) => id !== seat.id);
  } else {
    selectedSeats.value.push(seat.id);
  }
};

const bookSeats = async () => {
  if (selectedSeats.value.length === 0) {
    alert("Please select at least one seat.");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/api/bookings", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ flightId: flightId.value, seatIds: selectedSeats.value }),
    });

    if (response.ok) {
      alert("Seats booked successfully!");
    } else {
      alert("Booking failed. Try again.");
    }
  } catch (error) {
    console.error("Error booking seats:", error);
  }
};

onMounted(fetchSeats);
</script>

<template>
  <div class="seats-container">
    <h1>Select Your Seats</h1>
    <div v-if="seats.length" class="seat-grid">
      <div
          v-for="seat in seats"
          :key="seat.id"
          :class="{ seat: true, selected: selectedSeats.includes(seat.id), reserved: seat.isReserved }"
          @click="!seat.isReserved && toggleSeatSelection(seat)"
      >
        {{ seat.id }}
      </div>
    </div>
    <button @click="bookSeats" :disabled="selectedSeats.length === 0">Book Seats</button>
  </div>
</template>

<style scoped>
.seats-container {
  text-align: center;
  margin-top: 20px;
}

.seat-grid {
  display: grid;
  grid-template-columns: repeat(6, 50px);
  gap: 10px;
  justify-content: center;
  margin: 20px 0;
}

.seat {
  width: 50px;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #007bff;
  color: white;
  border-radius: 5px;
  cursor: pointer;
}

.seat.selected {
  background-color: #28a745;
}

.seat.reserved {
  background-color: #dc3545;
  cursor: not-allowed;
}

button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

button:disabled {
  background-color: gray;
}
</style>
