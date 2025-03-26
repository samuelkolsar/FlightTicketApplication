<template>
  <div class="booking-container">
    <!-- Hero Section -->
    <div class="hero">
      <h1 class="hero-title">Find Your Perfect Flight</h1>
      <p class="hero-subtitle">Book your next adventure with EstAviation</p>
    </div>

    <!-- Search Form -->
    <div class="search-form">
      <div class="form-group">
        <label for="from">From</label>
        <input
            type="text"
            id="from"
            placeholder="Enter departure city"
            v-model="search.from"
        >
      </div>

      <div class="form-group">
        <label for="to">To</label>
        <input
            type="text"
            id="to"
            placeholder="Enter destination"
            v-model="search.to"
        >
      </div>

      <div class="form-group">
        <label for="departure">Departure</label>
        <input
            type="date"
            id="departure"
            v-model="search.departure"
        >
      </div>

      <div class="form-group">
        <label for="return">Return</label>
        <input
            type="date"
            id="return"
            v-model="search.return"
        >
      </div>

      <div class="form-group passengers">
        <label for="passengers">Passengers</label>
        <select id="passengers" v-model="search.passengers">
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </div>

      <button class="search-button" @click="searchFlights">
        Search Flights
      </button>
    </div>

    <!-- Results Section -->
    <div class="results" v-if="showResults">
      <div class="result-card" v-for="flight in flights" :key="flight.id">
        <div class="flight-info">
          <div class="flight-times">
            <span class="departure">{{ flight.departure }}</span>
            <span class="duration">{{ flight.duration }}</span>
            <span class="arrival">{{ flight.arrival }}</span>
          </div>
          <div class="flight-price">
            <span class="amount">${{ flight.price }}</span>
            <button class="book-button" @click="bookFlight(flight)">
              Book Now
            </button>
          </div>
        </div>
        <div class="flight-details">
          <div class="airline">{{ flight.airline }}</div>
          <div class="stops">{{ flight.stops }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const search = ref({
  from: '',
  to: '',
  departure: '',
  return: '',
  passengers: '1'
})

const showResults = ref(false)
const flights = ref([])

const searchFlights = () => {
  // Simulate API call
  const mockFlights = [
    {
      id: 1,
      departure: '08:30',
      arrival: '10:45',
      duration: '2h 15m',
      price: 250,
      airline: 'EstAviation',
      stops: 'Non-stop'
    },
    {
      id: 2,
      departure: '12:15',
      arrival: '14:30',
      duration: '2h 15m',
      price: 280,
      airline: 'EstAviation',
      stops: 'Non-stop'
    }
  ]

  flights.value = mockFlights
  showResults.value = true

}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap");
:root {
  --primary-color: #3d5cb8;
  --primary-color-dark: #334c99;
  --text-dark: #0f172a;
  --text-light: #64748b;
  --extra-light: #f1f5f9;
  --white: #ffffff;
  --max-width: 1200px;
}
.booking-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 2rem;
}

.hero {
  text-align: center;
  margin-bottom: 3rem;
}

.hero-title {
  font-size: 2.5rem;
  margin-bottom: 1rem;
  color: var(--text-dark);
}

.hero-subtitle {
  font-size: 1.2rem;
  color: var(--text-light);
}

.search-form {
  background: var(--white);
  padding: 2rem;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: var(--text-dark);
  font-weight: 500;
}

input, select {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid var(--text-light);
  border-radius: 6px;
  font-size: 1rem;
}

.search-button {
  width: 100%;
  padding: 1rem;
  background: var(--primary-color);
  color: var(--white);
  border: none;
  border-radius: 6px;
  font-size: 1.1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.search-button:hover {
  background: var(--primary-color-dark);
}

.results {
  display: grid;
  gap: 1.5rem;
}

.result-card {
  background: var(--white);
  padding: 1.5rem;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
}

.flight-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.flight-times {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.duration {
  color: var(--text-light);
}

.flight-price {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.amount {
  font-size: 1.5rem;
  font-weight: 600;
}

.book-button {
  padding: 0.75rem 1.5rem;
  background: var(--primary-color);
  color: var(--white);
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.book-button:hover {
  background: var(--primary-color-dark);
}

.flight-details {
  display: flex;
  justify-content: space-between;
  color: var(--text-light);
  font-size: 0.9rem;
}

@media (max-width: 768px) {
  .booking-container {
    padding: 1rem;
  }

  .search-form {
    padding: 1rem;
  }

  .result-card {
    padding: 1rem;
  }
}
</style>