<!-- components/Header.vue -->
<template>
    <header class="section__container header__container">
      <h1 class="section__header">Book today<br />Make memories for life</h1>
      <img 
        src="../assets/header.jpg" 
        alt="header" 
        class="header__image"
      />
    </header>
  </template>
  
  <script>
  export default {
    name: 'HeaderComponent'
  }
  </script>
  
  <style scoped>
  .header__container {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 4rem 0;
    background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
                url('../assets/header.jpg');
    background-size: cover;
    background-position: center;
    color: white;
    margin-bottom: 50px;
    border-radius: 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.7);
  }
  
  .section__header {
    font-size: 3.5rem;
    font-weight: 700;
    margin-bottom: 2rem;
    text-align: center;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
    border-radius: 50px;
    text-shadow:
        2px 2px 8px rgba(74, 73, 91, 0.2),
        0 0 3px rgba(106, 108, 162, 0.7);
  }
  
  .header__image {
    width: 10%;
    height: 40%;
    object-fit: cover;
    opacity: 0;
    border-radius: 20px;
  }
  </style>