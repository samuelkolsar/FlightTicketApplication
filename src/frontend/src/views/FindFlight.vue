<template>
  <div class="find-flight">
    <Navbar />
    <Header />
    <BookingSection />
  </div>
</template>

<script>
import Navbar from '@/components/Navbar.vue'
import Header from '@/components/Header.vue'
import BookingSection from '@/components/Booking.vue'

export default {
  name: 'FindFlight',
  components: {
    Navbar,
    Header,
    BookingSection,
  }
}
</script>

<style scoped>
.find-flight {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
</style>