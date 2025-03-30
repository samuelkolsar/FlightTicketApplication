<template>
  <section class="choose-seats">
    <h2>Choose Your Seat</h2>
    <p>Select {{ numTravellers }} seat<span v-if="numTravellers > 1">s</span>.</p>
    <div class="seating-plan">
      <div v-for="(row, rowIndex) in rows" :key="rowIndex" class="row">
        <div class="side left">
          <div v-for="seat in row.slice(0, seatsPerRow / 2)" 
               :key="seat.seatNumber"
               :class="['seat', { reserved: seat.reserved, selected: selectedSeats.includes(seat.seatNumber) }]"
               @click="toggleSeat(seat)">
            {{ seat.seatNumber }}
          </div>
        </div>
        <div class="aisle"></div>
        <div class="side right">
          <div v-for="seat in row.slice(seatsPerRow / 2)" 
               :key="seat.seatNumber"
               :class="['seat', { reserved: seat.reserved, selected: selectedSeats.includes(seat.seatNumber) }]"
               @click="toggleSeat(seat)">
            {{ seat.seatNumber }}
          </div>
        </div>
      </div>
    </div>
    <button class="btn confirm-btn" :disabled="selectedSeats.length !== numTravellers" @click="confirmSeats">
      Confirm
    </button>
  </section>
</template>

<script>
import axios from 'axios';
export default {
  name: 'ChooseSeats',
  data() {
    return {
      flightId: null,
      travelClass: '',
      numTravellers: 1,
      seats: [],
      selectedSeats: [] // now an array of seat IDs
    }
  },
  computed: {
    // Determine how many seats per row based on travel class.
    seatsPerRow() {
      if (this.travelClass === 'First Class') return 2;
      else if (this.travelClass === 'Business Class') return 4;
      else return 6; // Economy Class
    },
    // Group seats into rows of seatsPerRow.
    rows() {
      const rows = [];
      for (let i = 0; i < this.seats.length; i += this.seatsPerRow) {
        rows.push(this.seats.slice(i, i + this.seatsPerRow));
      }
      return rows;
    }
  },
  created() {
    // Retrieve flightId, travelClass, and travellers from route params.
    this.flightId = this.$route.params.flightId;
    this.travelClass = this.$route.params.selectedClass;
    this.numTravellers = parseInt(this.$route.params.travellers);
    this.fetchSeats();
  },
  methods: {
    fetchSeats() {
      // Fetch seat data from backend matching flight and travel class.
      axios.get('http://localhost:8080/api/flights/seats', { 
        params: { 
          flightId: this.flightId, 
          travelClass: this.convertTravelClass() 
        }
      })
      .then(response => {
        this.seats = response.data;
      })
      .catch(error => {
        console.error('Error fetching seats:', error);
      });
    },
    convertTravelClass() {
      // Convert travel class name into the numeric value expected by the backend:
      // 1 for First Class, 2 for Business Class, 3 for Economy Class.
      if (this.travelClass === 'First Class') return 1;
      if (this.travelClass === 'Business Class') return 2;
      return 3;
    },
    toggleSeat(seat) {
      if (seat.reserved) return;
      const seatNumber = seat.seatNumber; // use the actual seat number
      const index = this.selectedSeats.indexOf(seatNumber);
      // If already selected, remove it.
      if (index >= 0) {
        this.selectedSeats.splice(index, 1);
      } else {
        // Otherwise, if selection count is less than allowed, add the seat.
        if (this.selectedSeats.length < this.numTravellers) {
          this.selectedSeats.push(seatNumber);
        }
      }
    },
    confirmSeats() {
      console.log('Confirmed seats:', this.selectedSeats);
      axios.post('http://localhost:8080/api/flights/book', this.selectedSeats, { 
        params: { flightId: this.flightId },
        headers: { 'Content-Type': 'application/json' }
      })
        .then(response => {
          console.log('Booking confirmed:', response.data);
          alert('Booking confirmed!');
          // Redirect to the find flight page after booking confirmation.
          this.$router.push({ name: 'find-flight' });
        })
        .catch(error => {
          console.error('Error confirming booking:', error);
        });
    }
  }
}
</script>

<style scoped>
.choose-seats {
  padding: 2rem;
  text-align: center;
}
.seating-plan {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  margin-top: 2rem;
  align-items: center;
}
.row {
  display: flex;
  align-items: center;
  gap: 1rem;
}
.side {
  display: grid;
  grid-auto-flow: column;
  gap: 10px;
}
.aisle {
  width: 40px; /* Represents the walkway/aisle */
}
.seat {
  width: 50px;
  height: 50px;
  background-color: #4caf50;
  color: white;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
  box-sizing: border-box;
}
.seat:hover {
  background-color: #45a049;
}
.seat.reserved {
  background-color: gray;
  cursor: not-allowed;
  transition: none;
}
.seat.selected {
  background-color: #ff9800;
}
.btn.confirm-btn {
  margin-top: 2rem;
  padding: 0.75rem 1.5rem;
  background-color: #333;
  transition: background-color 0.3s ease;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}
.btn.confirm-btn:hover {
  background-color: #444;
}
.btn.confirm-btn:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}
</style>