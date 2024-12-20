<template>
    <div class="flex justify-center gap-6 mt-8">
      <!-- Total Clients Card -->
      <div class="bg-blue-100 text-blue-700 rounded-lg p-6 shadow-lg w-full sm:w-1/3 hover:transform hover:translate-y-1 transition-all">
        <div class="flex items-center mb-4">
          <!-- Client Icon -->
          <UserIcon class="w-8 h-8 text-blue-600" />
          <h3 class="text-xl font-semibold ml-3">Total Clients</h3>
        </div>
        <p class="text-3xl font-bold">{{ totalClients }}</p>
      </div>
  
      <!-- Total Sales Card -->
      <div class="bg-green-100 text-green-700 rounded-lg p-6 shadow-lg w-full sm:w-1/3 hover:transform hover:translate-y-1 transition-all">
        <div class="flex items-center mb-4">
          <!-- Dollar Icon -->
          <CurrencyDollarIcon class="w-8 h-8 text-green-600" />
          <h3 class="text-xl font-semibold ml-3">Total Sales</h3>
        </div>
        <p class="text-3xl font-bold">{{ totalSales }}</p>
      </div>
  
      <!-- Total Stores Card -->
      <div class="bg-yellow-100 text-yellow-700 rounded-lg p-6 shadow-lg w-full sm:w-1/3 hover:transform hover:translate-y-1 transition-all">
        <div class="flex items-center mb-4">
          <!-- Store Icon -->
          <ShoppingBagIcon class="w-8 h-8 text-yellow-600" />
          <h3 class="text-xl font-semibold ml-3">Total Stores</h3>
        </div>
        <p class="text-3xl font-bold">{{ totalStores }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  // Import icons from Heroicons
  import { UserIcon, CurrencyDollarIcon, ShoppingBagIcon } from '@heroicons/vue/outline';
  
  export default {
    name: "Numbers",
    components: {
      UserIcon,
      CurrencyDollarIcon,
      ShoppingBagIcon,
    },
    data() {
      return {
        totalClients: 0,
        totalSales: 0,
        totalStores: 0,
      };
    },
    mounted() {
      this.fetchTotals();
    },
    methods: {
      async fetchTotals() {
        try {
          const response = await axios.get('http://localhost:8081/api/admin/stats');
          this.totalClients = response.data[0];
          this.totalSales = response.data[1];
          this.totalStores = response.data[2];
        } catch (error) {
          console.error("Error fetching totals:", error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  /* No custom styles needed, Tailwind CSS handles it */
  </style>
  