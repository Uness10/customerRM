<template>
    <div :class="['sidebar', { folded: isFolded }]">
      <div class="sidebar-header">
        <h2 v-if="!isFolded">My App</h2>
        <button class="toggle-btn" @click="toggleSidebar">
          <span v-if="isFolded">☰</span>
          <span v-else>×</span>
        </button>
      </div>
      <ul class="sidebar-links" v-show="!isFolded">
        <li><router-link to="/dashboard" class="sidebar-link">Dashboard</router-link></li>
        <li><router-link to="/sales" class="sidebar-link">Sales</router-link></li>
        <li><router-link to="/customers" class="sidebar-link">Customers</router-link></li>
        <li><router-link to="/inventory" class="sidebar-link">Inventory</router-link></li>
        <li><router-link to="/settings" class="sidebar-link">Settings</router-link></li>
        <!-- Add more links here -->
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'Sidebar',
    data() {
      return {
        isFolded: false, // Controls whether the sidebar is folded
      };
    },
    methods: {
      toggleSidebar() {
        this.isFolded = !this.isFolded;
      },
    },
  };
  </script>
  
  <style scoped>
  .sidebar {
    width: 250px;
    height: 100vh;
    background-color: #333;
    color: white;
    padding: 20px;
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    flex-direction: column;
    transition: width 0.3s ease-in-out;
  }
  
  .sidebar.folded {
    width: 60px; /* When folded, set a smaller width */
  }
  
  .sidebar-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
  }
  
  .sidebar-header h2 {
    font-size: 24px;
    margin: 0;
    transition: opacity 0.3s ease-in-out;
  }
  
  .sidebar-header.folded h2 {
    opacity: 0; /* Hide the title when folded */
  }
  
  .toggle-btn {
    background: none;
    border: none;
    color: white;
    font-size: 30px;
    cursor: pointer;
    padding: 5px;
  }
  
  .sidebar-links {
    list-style-type: none;
    padding: 0;
    margin-top: 10px;
  }
  
  .sidebar-link {
    display: block;
    color: white;
    text-decoration: none;
    padding: 10px;
    margin: 10px 0;
    border-radius: 4px;
    transition: background-color 0.3s ease;
  }
  
  .sidebar-link:hover {
    background-color: #555;
  }
  
  /* Hide the links when the sidebar is folded */
  .sidebar.folded .sidebar-links {
    display: none;
  }
  </style>
  