<template>
  <div class="clients-section">
    <h1 class="section-title">Manage Clients</h1>

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
        <button @click="getHighSpenders" class="filter-btn">High Spenders</button>
        <button @click="getInactiveClients" class="filter-btn">Inactive Clients</button>
      </div>
    </div>

    <!-- Clients Table -->
    <div v-if="clients.length" class="clients-table-container">
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
            <td class="action-buttons">
              <button @click="viewClient(client.id)" class="view-btn">View</button>
              <button @click="deleteClient(client.id)" class="delete-btn">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-else class="no-clients">No clients found.</div>

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
  padding :5%;
  padding-left: 12%;
  padding-right: 5%;
  
  background-color: #f8f9fa;
  border-radius: 10px;
}

.section-title {
  font-size: 2rem;
  font-weight: 700;
  color: #343a40;
  margin-bottom: 20px;
  text-align: center;
}

.search-filter {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  align-items: center;
}

.search-input {
  padding: 10px;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 30%;
  margin-right: 10%;
}

.filter-buttons {
  display: flex;
  gap: 10px;
}

.filter-btn {
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

.filter-btn:hover {
  background-color: #0056b3;
}

.clients-table-container {
  margin-top: 20px;
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

.clients-table td {
  vertical-align: middle;
}

.action-buttons button {
  padding: 5px 10px;
  margin: 0 5px;
  border-radius: 5px;
  cursor: pointer;
}

.view-btn {
  background-color: #28a745;
  color: white;
}

.view-btn:hover {
  background-color: #218838;
}

.delete-btn {
  background-color: #dc3545;
  color: white;
}

.delete-btn:hover {
  background-color: #c82333;
}

.no-clients {
  font-size: 1.2rem;
  color: #6c757d;
  text-align: center;
}

.add-client-btn {
  margin-top: 2vh;
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  width: 30%;
  text-align: center;
  cursor: pointer;
}

.add-client-btn:hover {
  background-color: #218838;
}

/* Responsive Design */
@media (max-width: 768px) {
  .clients-section {
    margin: 0 10px;
    padding: 15px;
  }

  .search-input {
    width: 200px;
  }

  .filter-buttons {
    display: flex;
    flex-wrap: wrap;
  }

  .filter-btn {
    margin-top: 5px;
    width: 100%;
  }

  .clients-table th, .clients-table td {
    font-size: 0.9rem;
  }

  .action-buttons button {
    padding: 4px 8px;
  }
}

  </style>
  