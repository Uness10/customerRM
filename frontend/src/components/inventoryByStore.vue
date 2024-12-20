<template>
<div class="max-w-8xl  p-6 bg-gray-100 rounded-lg shadow-lg mr-8">
  <h1 class="text-3xl font-semibold text-center mb-6 text-gray-800">Inventory by Store</h1>

    <div class="flex space-x-6">

      <div class="flex-1 p-6 bg-white rounded-lg shadow-md">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Add Inventory</h2>
        <form @submit.prevent="addInventory">
          <div class="space-y-4">
            <!-- Product Selection -->
            <div>
              <label for="product" class="block text-sm font-medium text-gray-700">Select Product:</label>
              <select
                id="product"
                v-model="newInventory.product"
                :disabled="!storeId" 
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              >
                <option value="" disabled>Select a product</option>
                <option v-for="product in products" :key="product.id" :value="product">
                  {{ product.name }}
                </option>
              </select>
            </div>

            <!-- Quantity Input -->
            <div>
              <label for="quantity" class="block text-sm font-medium text-gray-700">Quantity:</label>
              <input
                id="quantity"
                type="number"
                v-model="newInventory.quantity"
                min="1"
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              />
            </div>

            <!-- Submit Button -->
            <button type="submit" :disabled="!storeId" class="w-full py-2 bg-indigo-600 text-white font-medium rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 disabled:bg-gray-400">
              Add Inventory
            </button>
          </div>
        </form>
      </div>

      <!-- Right Section for Filters and Inventory Table (Column Layout) -->
      <div class="flex-2 space-y-6 w-2/3">

        <!-- Store Selection and Filters -->
        <div class="p-6 bg-white rounded-lg shadow-md">
          <div class="space-y-4">
            <!-- Store Selection -->
            <div>
              <label for="store" class="block text-sm font-medium text-gray-700">Select Store:</label>
              <select
                id="store"
                v-model="storeId"
                @change="fetchInventory"
                class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              >
                <option value="" disabled>Select a store</option>
                <option v-for="store in stores" :key="store.id" :value="store.id">
                  {{ store.name }}
                </option>
              </select>
            </div>

            <!-- Quantity and Price Filters -->
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label for="min-quantity" class="block text-sm font-medium text-gray-700">Min Quantity:</label>
                <input
                  id="min-quantity"
                  type="number"
                  v-model="minQuantity"
                  placeholder="Min Quantity"
                  @input="fetchInventory"
                  class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
              </div>

              <div>
                <label for="max-price" class="block text-sm font-medium text-gray-700">Max Price:</label>
                <input
                  id="max-price"
                  type="number"
                  v-model="maxPrice"
                  placeholder="Max Price"
                  @input="fetchInventory"
                  class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                />
              </div>
            </div>
          </div>
        </div>

        <!-- Inventory Table -->
        <div class="bg-white rounded-lg shadow-md overflow-x-auto">
          <table class="min-w-full table-auto">
            <thead class="bg-indigo-600 text-white">
              <tr>
                <th class="px-4 py-2 text-left">Product Name</th>
                <th class="px-4 py-2 text-left">Quantity</th>
                <th class="px-4 py-2 text-left">Price</th>
              </tr>
            </thead>
            <tbody>
              <tr v-if="inventory.length === 0">
                <td colspan="3" class="px-4 py-2 text-center text-gray-500 italic">No inventory data available</td>
              </tr>
              <tr v-for="item in inventory" :key="item.id">
                <td class="px-4 py-2">{{ item.product.name }}</td>
                <td class="px-4 py-2">{{ item.quantity }}</td>
                <td class="px-4 py-2">${{ item.product.price.toFixed(2) }}</td>
              </tr>
            </tbody>
          </table>
        </div>

      </div>

    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "InventoryByStore",
  data() {
    return {
      stores: [],
      products: [],
      inventory: [],
      storeId: null,
      minQuantity: null,
      maxPrice: null,
      newInventory: {
        product: null,
        quantity: null,
      },
    };
  },
  methods: {
    async fetchStores() {
      try {
        const response = await axios.get("http://localhost:8081/api/stores/all");
        this.stores = response.data;
      } catch (error) {
        console.error("Error fetching stores:", error);
        alert("Failed to fetch stores. Please try again.");
      }
    },

    async fetchProducts() {
      try {
        const response = await axios.get("http://localhost:8081/api/products/all");
        this.products = response.data;
      } catch (error) {
        console.error("Error fetching products:", error);
        alert("Failed to fetch products. Please try again.");
      }
    },

    async fetchInventory() {
      if (!this.storeId) return;

      const params = {};
      if (this.minQuantity) params.minQuantity = this.minQuantity;
      if (this.maxPrice) params.maxPrice = this.maxPrice;

      try {
        const response = await axios.get(
          `http://localhost:8081/api/stores/${this.storeId}/inventory`,
          { params }
        );
        this.inventory = response.data;
      } catch (error) {
        console.error("Error fetching inventory:", error);
        alert("Failed to fetch inventory. Please try again.");
      }
    },

    async addInventory() {
      if (!this.newInventory.product || !this.newInventory.quantity) {
        alert("Please fill in all fields.");
        return;
      }

      try {
        const response = await axios.post(
          `http://localhost:8081/api/stores/${this.storeId}/addInventory`,
          this.newInventory
        );
        location.reload();
      } catch (error) {
        console.error("Error adding inventory:", error);
        alert("Failed to add inventory. Please try again.");
      }
    },
    
  },
  mounted() {
    this.fetchStores();
    this.fetchProducts();
  },
};
</script>

<style scoped>
/* You can remove the previous custom styles, Tailwind already handles most of it */
</style>
