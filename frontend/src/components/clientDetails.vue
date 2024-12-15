<template>
    <div class="client-details">
      <h1 class="client-name">{{ client.name }}'s Details</h1>
  
      <!-- Client Information -->
      <div class="client-info">
        <p><strong>Contact Info:</strong> {{ client.contactInfo }}</p>
        <p><strong>Total Spend:</strong> ${{ totalSpend }}</p>
        <p><strong>Last Purchase Date:</strong> {{ lastPurchaseDate }}</p>
      </div>
  
      <!-- Purchase History -->
      <div class="purchase-history">
        <h2>Purchase History</h2>
        <table class="purchase-history-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Total Amount</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="sale in purchaseHistory" :key="sale.id">
              <td>{{ sale.date }}</td>
              <td>${{ sale.totalAmount }}</td>
            </tr>
          </tbody>
        </table>
      </div>
  
      <button @click="closeDetails" class="close-btn">Close</button>
    </div>
  </template>
  
  <script>
    import axios from 'axios';
  export default {
    data() {
      return {
        client: {},
        totalSpend: 0,
        lastPurchaseDate: '',
        purchaseHistory: [],
      };
    },
    props: {
      clientId: {
        type: Number,
        required: true
      }
    },
    methods: {
      fetchClientDetails() {
        axios.get(`http://localhost:8081/api/clients/${this.clientId}`)
          .then(response => {
            this.client = response.data;
            this.fetchPurchaseHistory();
          });
      },
      fetchPurchaseHistory() {
        axios.get(`http://localhost:8081/api/clients/history/${this.clientId}`)
          .then(response => {
            this.purchaseHistory = response.data;
            this.calculateTotalSpend();
          });
      },
      calculateTotalSpend() {
        this.totalSpend = this.purchaseHistory.reduce((total, sale) => total + sale.totalAmount, 0);
        this.lastPurchaseDate = this.purchaseHistory.length > 0 ? this.purchaseHistory[0].date : 'No purchases yet';
      },
      closeDetails() {
        this.$emit('close');
      }
    },
    created() {
      this.fetchClientDetails();
    }
  };
  </script>
  
  <style scoped>
  .client-details {
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .client-name {
    font-size: 2rem;
    font-weight: bold;
    margin-bottom: 20px;
  }
  
  .client-info {
    font-size: 1.2rem;
    margin-bottom: 30px;
  }
  
  .purchase-history table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .purchase-history th, .purchase-history td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
  }
  
  .purchase-history th {
    background-color: #f1f1f1;
  }
  
  .close-btn {
    padding: 12px 20px;
    background-color: #ccc;
    color: white;
    font-size: 1rem;
    cursor: pointer;
    border: none;
    border-radius: 5px;
  }
  
  .close-btn:hover {
    background-color: #999;
  }
  </style>
  