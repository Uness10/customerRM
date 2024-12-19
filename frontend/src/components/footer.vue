<template>
    <footer class="footer">
      <p>&copy; 2024 CRM . All rights reserved.</p>
    </footer>
  </template>
  
  <script>
  export default {
    name: 'Footer',
  };
  </script>
  
  <style scoped>
  .footer {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 10px 20px;
    bottom: 0;
    left: 0;
    width: 100%; /* Full width of the viewport */
    z-index: 10; /* Ensure it is above other components */
  }
  </style>
  