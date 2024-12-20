<template>
<div class="p-6 bg-gray-100 rounded-lg shadow-md" style="margin-left: 10%;">
  <!-- Title -->
    <h1 class="text-2xl font-bold text-gray-800 mb-6">Sales Management</h1>

    <!-- Sale Search -->
    <div class="mb-4">
      <input
        type="text"
        v-model="searchQuery"
        placeholder="Search by Sale ID, Client Name, or Date"
        class="w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300"
      />
    </div>

    <!-- Sorting Sales -->
    <div class="mb-4 flex items-center space-x-4">
      <label for="sort" class="text-sm font-medium text-gray-700">
        Sort By:
      </label>
      <select
        id="sort"
        v-model="sortBy"
        class="px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300"
      >
        <option value="date">Date</option>
        <option value="amount">Total Amount</option>
        <option value="client">Client Name</option>
      </select>
    </div>

    <!-- Sales Table -->
    <div class="mb-6">
      <h2 class="text-lg font-bold text-gray-700 mb-4">All Sales</h2>
      <table class="w-full table-auto border border-gray-300 text-left">
        <thead>
          <tr class="bg-gray-200 text-gray-700">
            <th class="px-4 py-2">Sale ID</th>
            <th class="px-4 py-2">Client Name</th>
            <th class="px-4 py-2">Date</th>
            <th class="px-4 py-2">Total Amount</th>
            <th class="px-4 py-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="sale in sortedSales"
            :key="sale.id"
            class="hover:bg-gray-50"
          >
            <td class="px-4 py-2 border-t border-gray-300">{{ sale.id }}</td>
            <td class="px-4 py-2 border-t border-gray-300">
              {{ sale.client.name }}
            </td>
            <td class="px-4 py-2 border-t border-gray-300">
              {{ formatDate(sale.date) }}
            </td>
            <td class="px-4 py-2 border-t border-gray-300">
              {{ sale.totalAmount.toFixed(2) }} USD
            </td>
            <td class="px-4 py-2 border-t border-gray-300 flex space-x-2">
              <button
                @click="viewSaleDetails(sale.id)"
                class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring focus:ring-blue-300"
              >
                View Details
              </button>
              <button
                @click="deletesale(sale.id)"
                class="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-700 focus:outline-none focus:ring focus:ring-red-300"
              >
                Delete
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Add Sale Section -->
    <button
      class="px-6 py-2 bg-green-500 text-white rounded-md hover:bg-green-700 focus:outline-none focus:ring focus:ring-green-300"
      @click="toggleComponent('c1')"
      v-show="!c1"
    >
      Record A Sale
    </button>

    <!-- Record Sale Component -->
    <RecordSale v-if="c1" @close="toggleComponent('c1')" />

    <!-- Sale Details Component -->
    <saleD
      v-if="selectedSaleId"
      :saleId="selectedSaleId"
      @close="clearSelectedClient"
    />
  </div>
</template>

<script>
import axios from "axios";
import RecordSale from "@/components/RecordSale.vue";
import saleD from "@/components/saleDetail.vue";

export default {
  name: "Sales",
  components: {
    RecordSale,
    saleD,
  },
  data() {
    return {
      sales: [],
      clients: [],
      products: [],
      isCollapsed: false,
      c1: false,
      c2: false,
      selectedSaleId: null,
      loading: true,
      searchQuery: "",
      sortBy: "date",
    };
  },
  computed: {
    filteredSales() {
      if (this.loading) return [];
      return this.sales.filter((sale) => {
        const query = this.searchQuery?.toLowerCase() || "";
        const clientName = sale.client?.name?.toLowerCase() || "";
        const saleId = sale.id?.toString() || "";
        const saleDate = this.formatDate(sale.date) || "";

        return (
          saleId.includes(query) ||
          clientName.includes(query) ||
          saleDate.includes(query)
        );
      });
    },
    sortedSales() {
      const salesCopy = [...this.filteredSales];
      switch (this.sortBy) {
        case "date":
          return salesCopy.sort((a, b) => new Date(a.date) - new Date(b.date));
        case "amount":
          return salesCopy.sort((a, b) => b.totalAmount - a.totalAmount);
        case "client":
          return salesCopy.sort((a, b) =>
            a.client.name.localeCompare(b.client.name)
          );
        default:
          return salesCopy;
      }
    },
  },
  methods: {
    fetchSales() {
      axios
        .get("http://localhost:8081/api/sales/all")
        .then((response) => {
          this.sales = response.data;
          this.loading = false;
        })
        .catch((error) => {
          console.error("Error fetching sales:", error);
          this.loading = false;
        });
    },
    fetchClients() {
      axios.get("http://localhost:8081/api/clients/all").then((response) => {
        this.clients = response.data;
      });
    },
    clearSelectedClient() {
      this.selectedSaleId = null;
    },
    fetchProducts() {
      axios.get("http://localhost:8081/api/products/all").then((response) => {
        this.products = response.data;
      });
    },
    deletesale(id) {
      axios
        .delete(`http://localhost:8081/api/sales/delete/${id}`)
        .then(() => {
          this.fetchSales();
        })
        .catch((error) => {
          console.error("Error deleting sale:", error);
        });
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString();
    },
    viewSaleDetails(id) {
      this.selectedSaleId = id;
    },
    toggleComponent(componentKey) {
      this[componentKey] = !this[componentKey];
    },
  },
  created() {
    this.fetchSales();
    this.fetchClients();
    this.fetchProducts();
  },
};
</script>

<style scoped>
/* Tailwind handles styling; scoped styles are unnecessary. */
</style>
