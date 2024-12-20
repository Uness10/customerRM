<template>
  <div class="sale-details p-6 bg-white rounded-lg shadow-md max-w-3xl mx-auto">
    <h1 class="text-2xl font-semibold mb-6">Sale Details</h1>
    
    <div v-if="sale">
      <!-- Sale Information -->
      <div class="sale-info mb-4">
        <p><strong class="font-medium">Sale Date:</strong> {{ formatDate(sale.date) }}</p>
        <p><strong class="font-medium">Total Amount:</strong> ${{ sale.totalAmount.toFixed(2) }}</p>
      </div>

      <!-- Item Details -->
      <div v-if="sale.items && sale.items.length" class="item-details mt-6">
        <h2 class="text-xl font-semibold mb-4">Items in this Sale</h2>
        <table class="item-table w-full table-auto border-collapse">
          <thead>
            <tr class="bg-gray-100">
              <th class="px-4 py-2 text-left">Item Name</th>
              <th class="px-4 py-2 text-left">Quantity</th>
              <th class="px-4 py-2 text-left">Price</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="item in sale.items" :key="item.id" class="border-b">
              <td class="px-4 py-2">{{ item.inventoryItem.product.name }}</td>
              <td class="px-4 py-2">{{ item.quantity }}</td>
              <td class="px-4 py-2">${{ item.inventoryItem.product.price.toFixed(2) }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <button @click="closeDetails" class="close-btn mt-6 px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700">
      Close
    </button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      sale: null,
    };
  },
  props: {
    saleId: {
      type: Number,
      required: true,
    },
  },
  methods: {
    fetchSaleDetails() {
      axios
        .get(`http://localhost:8081/api/sales/${this.saleId}`)
        .then((response) => {
          this.sale = response.data;  // Properly assign the response data to sale
        })
        .catch((error) => {
          console.error("Error fetching sale details:", error);
        });
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString();
    },
    closeDetails() {
      this.$emit('close');
    },
  },
  created() {
    this.fetchSaleDetails();
  },
};
</script>

<style scoped>
/* No custom styles needed, as Tailwind handles the styling */
</style>
