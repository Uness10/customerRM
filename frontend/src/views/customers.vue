<template>
    <div class="clients-section">
      <h1 class="section-title">All Clients</h1>
      
      <!-- Search and Filter Section -->
      <div class="search-filter">
        <input 
          v-model="searchQuery" 
          @input="searchClients" 
          type="text" 
          class="search-input" 
          placeholder="Search Clients by Name" 
        />
        
        <div class="filter-buttons">
          <button @click="getHighSpenders">High Spenders</button>
          <button @click="getInactiveClients">Inactive Clients</button>
        </div>
      </div>
      
      <!-- Clients Table -->
      <table class="clients-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Contact Info</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="client in clients" :key="client.id">
            <td>{{ client.name }}</td>
            <td>{{ client.contactInfo }}</td>
            <td>
              <button @click="viewClient(client.id)">View</button>
              <button @click="deleteClient(client.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
  
      <!-- Add Client Button -->
      <button @click="toggleAddClientForm" class="add-client-btn">Add New Client</button>
  
      <!-- Conditional rendering for Add/Edit Client Form -->
      <AddClient v-if="showAddClientForm" @close="toggleAddClientForm" />
  
      <!-- Conditional rendering for Client Details -->
      <ClientDetails v-if="selectedClientId" :clientId="selectedClientId" @close="clearSelectedClient" />
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import AddClient  from '@/components/addClient.vue';
  import ClientDetails from '@/components/clientDetails.vue';
  
  export default {
    components: {
      AddClient,
      ClientDetails
    },
    data() {
      return {
        clients: [],
        searchQuery: '',
        selectedClientId: null,
        showAddClientForm: false,
      };
    },
    methods: {
      fetchAllClients() {
        axios.get("http://localhost:8081/api/clients/all")
          .then(response => {
            this.clients = response.data;
          });
      },
      searchClients() {
        if (this.searchQuery) {
          axios.get(`http://localhost:8081/api/clients/search?name=${this.searchQuery}`)
            .then(response => {
              this.clients = response.data;
            });
        } else {
          this.fetchAllClients();
        }
      },
      viewClient(id) {
        this.selectedClientId = id; // Open client details
      },
      clearSelectedClient() {
        this.selectedClientId = null; // Close client details
      },
      deleteClient(id) {
        axios.delete(`http://localhost:8081/api/clients/delete/${id}`)
          .then(() => {
            this.fetchAllClients(); // Refresh the client list after deletion
          });
      },
      toggleAddClientForm() {
        this.showAddClientForm = !this.showAddClientForm; // Toggle visibility of Add Client form
      },
      getHighSpenders() {
        const threshold = prompt("Enter the spending threshold:");
        axios.get(`http://localhost:8081/api/clients/highSpenders?threshold=${threshold}`)
          .then(response => {
            this.clients = response.data;
          });
      },
      getInactiveClients() {
        const days = prompt("Enter number of days of inactivity:");
        axios.get(`http://localhost:8081/api/clients/inactive?days=${days}`)
          .then(response => {
            this.clients = response.data;
          });
      },
    },
    created() {
      this.fetchAllClients();
    }
  };
  </script>
  
  <style scoped>
  .clients-section {
    margin-left : 5%;
    padding: 20px;
    background-color: #f8f9fa;
  }
  
  .section-title {
    font-size: 2rem;
    font-weight: bold;
    color: #343a40;
    margin-bottom: 20px;
  }
  
  .search-filter {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
  }
  
  .search-input {
    padding: 10px;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid #ccc;
    width: 300px;
  }
  
  .filter-buttons button {
    margin-left: 10px;
    padding: 10px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
  }
  
  .filter-buttons button:hover {
    background-color: #0056b3;
  }
  
  .clients-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .clients-table th, .clients-table td {
    padding: 10px;
    text-align: left;
    border-bottom: 1px solid #ddd;
  }
  
  .clients-table button {
    padding: 5px 10px;
    margin: 0 5px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
  }
  
  .clients-table button:hover {
    background-color: #218838;
  }
  
  .add-client-btn {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 5px;
  }
  
  .add-client-btn:hover {
    background-color: #218838;
  }
  </style>
  