<template>
  <section class="flight-list">
    <div v-if="loading" class="loading">Loading flights...</div>
    <div v-else>
      <div v-if="!flights.length" class="no-flights">
        No flights found.
      </div>
      <div v-else>
        <div v-for="flight in flights" :key="flight.id" class="flight-item">
          <h3>{{ flight.startPoint.city }} to {{ flight.endPoint.city }}</h3>
          <p>Departure: {{ formatDate(flight.departureTime) }}</p>
          <p>Flight Duration: {{ calculateFlightDuration(flight.departureTime, flight.arrivalTime) }}</p>
          <p>Price: {{ calculatePrice(flight.price) }}</p>
          <!-- Book button -->
          <button class="btn book-btn" @click="bookFlight(flight)">Book</button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'FlightList',
  props: {
    flights: {
      type: Array,
      default: () => []
    },
    loading: {
      type: Boolean,
      default: false
    },
    selectedClass: {
      type: String,
      default: 'Economy Class'
    },
    travellers: {
      type: Number,
      default: 1
    }
  },
  methods: {
    bookFlight(flight) {
      this.$router.push({ 
        name: 'ChooseSeats', 
        params: { flightId: flight.id, selectedClass: this.selectedClass, travellers: this.travellers }
      });
    },
    formatDate(dateStr) {
      const date = new Date(dateStr);
      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    },
    calculateFlightDuration(departure, arrival) {
      const dep = new Date(departure);
      const arr = new Date(arrival);
      const diffInMs = arr - dep;
      const diffInMinutes = Math.floor(diffInMs / 60000);
      const hours = Math.floor(diffInMinutes / 60);
      const minutes = diffInMinutes % 60;
      return `${hours}h ${minutes}m`;
    },
    // Adjust multiplier as needed
    calculatePrice(basePrice) {
      let multiplier = 1;
      if (this.selectedClass === 'Business Class') {
        multiplier = 2;
      } else if (this.selectedClass === 'First Class') {
        multiplier = 3;
      }
      return (basePrice * multiplier).toFixed(2);
    }
  }
}
</script>

<style scoped>
.flight-list {
  margin-top: 2rem;
}
.loading,
.no-flights {
  text-align: center;
  color: #666;
}
.flight-item {
  padding: 1rem;
  margin-bottom: 1rem;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}
.book-btn {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
}
.book-btn:hover {
  background-color: #0056b3;
}
</style>