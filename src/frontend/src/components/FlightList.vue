<template>
  <section class="flight-list">
    <div v-if="loading" class="loading">Loading flights...</div>
    <div v-else>
      <div class="price-filter" v-if="flights.length">
        <input
          id="priceFilter"
          type="range"
          v-model.number="priceFilter"
          :min="minPrice"
          :max="sliderMaxPrice"
          step="1"
        />
        <div class="slider-value">
          Selected Price: {{ formatCurrency(priceFilter) }}
        </div>
      </div>
      
      <div v-if="!filteredFlights.length" class="no-flights">
        No flights found.
      </div>
      <div v-else>
        <div v-for="flight in filteredFlights" :key="flight.id" class="flight-item">
          <h3>{{ flight.startPoint.city }} to {{ flight.endPoint.city }}</h3>
          <p>Departure: {{ formatDate(flight.departureTime) }}</p>
          <p>
            Flight Duration: {{ calculateFlightDuration(flight.departureTime, flight.arrivalTime) }}
          </p>
          <p>Price: {{ formatCurrency(calculatePrice(flight.price)) }}</p>
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
  data() {
    return {
      priceFilter: 0
    }
  },
  computed: {
    filteredFlights() {
      return this.flights.filter(flight => {
        const price = Number(this.calculatePrice(flight.price));
        return price <= this.priceFilter;
      });
    },
    minPrice() {
      if (!this.flights.length) return 0;
      const prices = this.flights.map(flight => Number(this.calculatePrice(flight.price)));
      return Math.min(...prices);
    },
    maxPrice() {
      if (!this.flights.length) return 0;
      const prices = this.flights.map(flight => Number(this.calculatePrice(flight.price)));
      return Math.max(...prices);
    },
    sliderMaxPrice() {
      // Extend max price by 10%
      return this.maxPrice ? Math.ceil(this.maxPrice * 1.1) : 0;
    }
  },
  watch: {
    flights: {
      handler(newFlights) {
        if (newFlights && newFlights.length) {
          this.priceFilter = this.maxPrice;
        }
      },
      immediate: true
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
    calculatePrice(basePrice) {
      let multiplier = 1;
      if (this.selectedClass === 'Business Class') {
        multiplier = 2;
      } else if (this.selectedClass === 'First Class') {
        multiplier = 3;
      }
      return (basePrice * multiplier).toFixed(2);
    },
    formatCurrency(value) {
      return '€' + value;
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
  padding: 0.5rem 0.5rem;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 1rem;
}

.book-btn:hover {
  background-color: #0056b3;
}

/* Styles for the price filter slider */
.price-filter {
  margin-bottom: 1.5rem;
  text-align: center;
}
.price-filter label {
  font-size: 1rem;
  display: block;
  margin-bottom: 0.5rem;
}
.price-filter input[type="range"] {
  width: 80%;
  max-width: 400px;
}
.slider-value {
  margin-top: 0.5rem;
  font-weight: 600;
  color: #333;
  font-size: 1rem;
}
</style>