<script setup>
import { RouterView, RouterLink } from "vue-router";
</script>

<template>
  <div class="app">
    <!-- Navigation Bar -->
    <nav>
      <div class="nav-logo">EstAviation</div>
      <div class="nav-links-container">
        <RouterLink to="/" class="nav-link">Home</RouterLink>
        <RouterLink to="/search" class="nav-link">Destinations</RouterLink>
      </div>
    </nav>

    <!-- Main Content -->
    <main class="content">
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.app {
  font-family: Arial, sans-serif;
  text-align: center;
}

nav {
  max-width: var(--max-width);
  margin: auto;
  padding: 1rem;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.nav-logo {
  font-size: 1.5rem;
  font-weight: 600;
  color: var(--text-dark);
}

.nav-links-container {
  display: flex;
  gap: 2rem;
  margin: 0 auto;
}
.nav-link {

}

</style>
