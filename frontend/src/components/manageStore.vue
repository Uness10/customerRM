<template>
    <div class="p-6 bg-white rounded-lg shadow-md">
      <!-- Title -->
      <h1 class="text-2xl font-bold text-gray-800 mb-6">Store Management</h1>
  
      <!-- Search Store -->
      <div class="mb-4">
        <input
          type="text"
          v-model="searchQuery"
          placeholder="Search by Store Name or Location"
          class="w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
        />
      </div>
  
      <!-- Add New Store Button -->
      <button
        @click="openForm()"
        class="px-6 py-2 mb-6 bg-blue-600 text-white font-medium rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        Add New Store
      </button>
  
      <!-- Store List -->
      <div v-if="limitedStores.length" class="space-y-4">
        <h2 class="text-xl font-semibold text-gray-800">All Stores</h2>
        <ul class="space-y-2">
          <li
            v-for="store in limitedStores"
            :key="store.id"
            class="flex justify-between items-center p-4 bg-gray-100 rounded-md shadow-sm"
          >
            <strong class="text-gray-700">{{ store.name }}</strong>
            <span class="text-gray-500">{{ store.location }}</span>
          </li>
        </ul>
      </div>
      <div v-else>
        <p class="text-gray-500">No stores match your search. Try again.</p>
      </div>
  
      <!-- Add/Edit Store Form -->
      <add-store-form
        v-if="showForm"
        :store-id="currentStoreId"
        @close="closeForm"
      />
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import AddStoreForm from "@/components/addStore.vue";
  
  export default {
    name: "manageStore",
    components: {
      AddStoreForm,
    },
    data() {
      return {
        stores: [],
        showForm: false,
        currentStoreId: null, // Used for editing a specific store
        searchQuery: "", // Search input
      };
    },
    computed: {
      filteredStores() {
        // Filter stores based on search query
        const query = this.searchQuery.toLowerCase();
        return this.stores.filter(
          (store) =>
            store.name.toLowerCase().includes(query) ||
            store.location.toLowerCase().includes(query)
        );
      },
      limitedStores() {
        // Limit the number of displayed stores to 20
        return this.filteredStores.slice(0, 20);
      },
    },
    methods: {
      fetchStores() {
        // Fetch all stores from the backend
        axios.get("http://localhost:8081/api/stores/all").then((response) => {
          this.stores = response.data;
        });
      },
      openForm(storeId = null) {
        // Open the form for adding or editing
        this.currentStoreId = storeId;
        this.showForm = true;
      },
      closeForm() {
        // Close the form and refresh the store list
        this.showForm = false;
        this.fetchStores();
      },
    },
    created() {
      // Fetch stores when the component is created
      this.fetchStores();
    },
  };
  </script>
  
  <style scoped>
  /* No custom styles are necessary; Tailwind CSS handles everything */
  </style>
  