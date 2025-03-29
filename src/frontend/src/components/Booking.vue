<!-- components/BookingSection.vue -->
<template>
  <section class="section__container booking__container">
    <div class="booking__nav">
      <span 
        v-for="classOption in classOptions"
        :key="classOption"
        :class="{ active: selectedClass === classOption }"
        @click="selectClass(classOption)"
      >
        {{ classOption }}
      </span>
    </div>
    
    <form @submit.prevent="handleSubmit">
      <div class="form__group">
        <span><i class="ri-map-pin-line"></i></span>
        <div class="input__content">
          <div class="input__group">
            <input 
              type="text" 
              v-model="formData.source"
              @focus="handleInputFocus"
              @blur="handleInputBlur"
            />
            <label>Source</label>
          </div>
          <p>Where are you starting from?</p>
        </div>
      </div>

      <div class="form__group">
        <span><i class="ri-map-pin-line"></i></span>
        <div class="input__content">
          <div class="input__group">
            <input 
              type="text" 
              v-model="formData.location"
              @focus="handleInputFocus"
              @blur="handleInputBlur"
            />
            <label>Destination</label>
          </div>
          <p>Where are you going?</p>
        </div>
      </div>

      <div class="form__group">
        <span><i class="ri-user-3-line"></i></span>
        <div class="input__content">
          <div class="input__group">
            <input 
              type="number" 
              v-model.number="formData.travellers"
              @focus="handleInputFocus"
              @blur="handleInputBlur"
            />
            <label>Travellers</label>
          </div>
          <p>Add guests</p>
        </div>
      </div>

      <div class="form__group">
        <span><i class="ri-calendar-line"></i></span>
        <div class="input__content">
          <div class="input__group">
            <input 
              type="date" 
              v-model="formData.departureDate"
              @focus="handleInputFocus"
              @blur="handleInputBlur"
            />
            <label>Departure</label>
          </div>
          <p>Add date</p>
        </div>
      </div>
      
      <!-- Search button at the bottom -->
      <button class="btn" type="submit">
        <i class="ri-search-line"></i>
      </button>
    </form>
    <!-- Pass the selectedClass as prop -->
    <FlightList :flights="flights" :loading="loading" :selected-class="selectedClass" :travellers="formData.travellers" />
  </section>
</template>

<script>
import axios from 'axios'
import FlightList from './FlightList.vue'

export default {
  name: 'BookingSection',
  components: { FlightList },
  data() {
    return {
      formData: {
        source: '', 
        location: '',
        travellers: '',
        departureDate: ''
      },
      flights: [],
      loading: false,
      classOptions: ['Economy Class', 'Business Class', 'First Class'], 
      selectedClass: 'Economy Class' 
    }
  },
  methods: {
    handleSubmit() {
      const params = {
        fromCity: this.formData.source,
        toCity: this.formData.location,
        departure: this.formData.departureDate, 
        selectedClass: this.selectedClass, 
        travellers: this.formData.travellers
      }
      
      this.loading = true;
      axios.get('http://localhost:8080/api/flights', { params })
        .then(response => {
          console.log('Flights received:', response.data)
          this.flights = response.data;
        })
        .catch(error => {
          console.error('Error fetching flights:', error)
        })
        .finally(() => {
          this.loading = false;
        })
    },
    handleInputFocus(event) {
      const inputGroup = event.target.closest('.input__group')
      if (inputGroup) {
        inputGroup.classList.add('focused')
      }
    },
    handleInputBlur(event) {
      const inputGroup = event.target.closest('.input__group')
      if (inputGroup) {
        inputGroup.classList.remove('focused')
      }
    },
    selectClass(classOption) {
      this.selectedClass = classOption;
      console.log('Selected Class:', this.selectedClass);
    }
  }
}
</script>

<style scoped>
.booking__container {
  padding: 4rem 2rem;
  background-color: #f5f5f5;
  border-radius: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

.booking__nav {
  display: flex;
  justify-content: center;
  gap: 2rem;
  margin-bottom: 3rem;
}

.booking__nav span {
  padding: 0.5rem 1rem;
  border-radius: 20px;
  background-color: #fff;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.booking__nav span:hover {
  background-color: #e0e0e0;
}

.booking__nav span.active {
  background-color: #333;
  color: #fff;
}

.form__group {
  position: relative;
  margin-bottom: 2rem;
}

.input__content {
  position: relative;
}

.input__group {
  position: relative;
  margin-bottom: 0.5rem;
}

.input__group input {
  width: 100%;
  padding: 0.8rem;
  border: none;
  border-bottom: 2px solid #ddd;
  background: transparent;
  font-size: 1rem;
}

.input__group label {
  position: absolute;
  left: 0;
  top: 50%;
  transform: translateY(-50%);
  color: #666;
  transition: all 0.3s ease;
  pointer-events: none;
}

.input__group input:focus + label,
.input__group input:not(:placeholder-shown) + label {
  top: -1.5rem;
  font-size: 0.8rem;
  color: #333;
}

.input__group.focused label {
  color: #333;
}

.form__group p {
  color: #666;
  font-size: 0.9rem;
}

.btn {
  display: inline-block;
  padding: 1rem;
  background-color: #333;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
  margin-top: -2rem;
  margin-bottom: 1rem;
  margin-left: 1rem; 
}

.btn:hover {
  background-color: #444;
}

.btn i {
  font-size: 1.5rem;
  margin-right: 0.5rem;
}

@media (max-width: 768px) {
  .booking__nav {
    flex-wrap: wrap;
    gap: 1rem;
  }
  
  .form__group {
    margin: 1rem 0;
  }
}
</style>