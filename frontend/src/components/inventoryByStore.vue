<template>
  <div class="inventory-container">
    <h1>Inventory by Store</h1>

    <!-- Content Wrapper for Flex Layout -->
    <div class="content-wrapper">
      <!-- Add Inventory Section (Left Side) -->
      <div class="add-inventory-section">
        <h2>Add Inventory</h2>
        <form @submit.prevent="addInventory">
          <div class="form-group">
            <label for="product">Select Product:</label>
            <select
              id="product"
              v-model="newInventory.product"
              :disabled="!storeId" 
              required
            >
              <option value="" disabled>Select a product</option>
              <option v-for="product in products" :key="product.id" :value="product">
                {{ product.name }}
              </option>
            </select>
          </div>
          
          <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input
              id="quantity"
              type="number"
              v-model="newInventory.quantity"
              min="1"
              required
            />
          </div>

          <button type="submit" :disabled="!storeId">Add Inventory</button> <!-- Disable submit button if no store is selected -->
        </form>
      </div>

      <!-- Right Section for Filters and Inventory Table (Column Layout) -->
      <div class="right-section">
        <!-- Store Selection and Filters -->
        <div class="filter-section">
          <div class="filter-group">
            <label for="store">Select Store:</label>
            <select id="store" v-model="storeId" @change="fetchInventory" required>
              <option value="" disabled>Select a store</option>
              <option v-for="store in stores" :key="store.id" :value="store.id">
                {{ store.name }}
              </option>
            </select>
          </div>

          <!-- Quantity and Price Filters -->
          <div class="filter-group">
            <label for="min-quantity">Min Quantity:</label>
            <input
              id="min-quantity"
              type="number"
              v-model="minQuantity"
              placeholder="Min Quantity"
              @input="fetchInventory"
            />

            <label for="max-price">Max Price:</label>
            <input
              id="max-price"
              type="number"
              v-model="maxPrice"
              placeholder="Max Price"
              @input="fetchInventory"
            />
          </div>
        </div>

        <!-- Inventory Table -->
        <div class="inventory-table-container">
          <table class="inventory-table">
            <thead>
              <tr>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price</th>
              </tr>
            </thead>
            <tbody>
              <tr v-if="inventory.length === 0">
                <td colspan="3" class="no-data">No inventory data available</td>
              </tr>
              <tr v-for="item in inventory" :key="item.id">
                <td>{{ item.product.name }}</td>
                <td>{{ item.quantity }}</td>
                <td>${{ item.product.price.toFixed(2) }}</td>
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
      stores: [], // List of stores
      products: [], // List of all products
      inventory: [], // Inventory data for the selected store
      storeId: null, // Selected store ID
      minQuantity: null, // Minimum quantity filter
      maxPrice: null, // Maximum price filter
      newInventory: {
        product: null, // Selected product for adding inventory
        quantity: null, // Quantity to be added
      },
    };
  },
  methods: {
    // Fetch list of stores from the backend
    async fetchStores() {
      try {
        const response = await axios.get("http://localhost:8081/api/stores/all");
        this.stores = response.data;
      } catch (error) {
        console.error("Error fetching stores:", error);
        alert("Failed to fetch stores. Please try again.");
      }
    },

    // Fetch all products for adding inventory
    async fetchProducts() {
      try {
        const response = await axios.get("http://localhost:8081/api/products/all");
        this.products = response.data;
      } catch (error) {
        console.error("Error fetching products:", error);
        alert("Failed to fetch products. Please try again.");
      }
    },

    // Fetch inventory items for the selected store with filters
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

    // Add inventory for the selected store
    async addInventory() {
      if (!this.newInventory.product || !this.newInventory.quantity) {
        alert("Please fill in all fields.");
        return;
      }

      try {
        console.log(this.newInventory);
        const response = await axios.post(
          `http://localhost:8081/api/stores/${this.storeId}/addInventory`,
          this.newInventory
        );
        this.inventory.push(newInventory); 
        this.newInventory.productId = null;
        this.newInventory.quantity = null;
        alert("Inventory added successfully!");
      } catch (error) {
        console.error("Error adding inventory:", error);
        alert("Failed to add inventory. Please try again.");
      }
    },
  },
  mounted() {
    this.fetchStores(); // Fetch stores when the component is mounted
    this.fetchProducts(); // Fetch all products when the component is mounted
  },
};
</script>

<style scoped>
.inventory-container {
  width : 80%;
  margin: 30px auto;
  padding: 20px;
  background-color: #f0f4f8;
  border-radius: 10px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
  font-family: 'Roboto', sans-serif;
}

h1, h2 {
  text-align: center;
  margin-bottom: 30px;
  font-size: 28px;
  color: #2c3e50;
}

.content-wrapper {
  display: flex;
  flex-direction: row;
}

.add-inventory-section {
  flex: 1;
  padding: 20px;
  background-color: #fff;
  margin-right: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.right-section {
  flex: 2;
  display: flex;
  flex-direction: column;
  width: 60%;
}

.filter-section, .inventory-table-container {
  margin-bottom: 30px;
}

.filter-group, .form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

label {
  font-weight: 600;
  margin-bottom: 8px;
  color: #34495e;
}

select,
input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 16px;
  margin-bottom: 12px;
  transition: border-color 0.3s;
}

select:focus,
input:focus {
  border-color: #3498db;
  outline: none;
}

button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  font-size: 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}

button:hover {
  background-color: #2980b9;
}

.inventory-table-container {
  overflow-x: auto;
}

.inventory-table {
  width: 100%;
  border-collapse: collapse;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.inventory-table th,
.inventory-table td {
  padding: 15px;
  text-align: left;
  border-bottom: 1px solid #ddd;
  color: #34495e;
}

.inventory-table th {
  background-color: #3498db;
  color: white;
  font-weight: bold;
  text-transform: uppercase;
}

.inventory-table tr:nth-child(even) {
  background-color: #f9f9f9;
}

.inventory-table tr:hover {
  background-color: #ecf0f1;
}

.no-data {
  text-align: center;
  color: #888;
  font-style: italic;
  padding: 20px;
}
</style>
