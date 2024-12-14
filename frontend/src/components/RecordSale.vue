<template>
  <div>
    <h2>Create a New Sale</h2>
    <form @submit.prevent="submitForm">
      <!-- Date Selection -->
      <div>
        <label for="date">Sale Date:</label>
        <input type="date" v-model="sale.date" id="date" required />
      </div>

      <!-- Store Selection -->
      <div>
        <label for="store">Store:</label>
        <select v-model="store" id="store" @change="fetchProductsByStore" required>
          <option v-for="store in stores" :key="store.id" :value="store">
            {{ store.name }}
          </option>
        </select>
      </div>

      <!-- Client Selection -->
      <div>
        <label for="client">Client:</label>
        <select v-model="sale.client" id="client" required>
          <option v-for="client in clients" :key="client.id" :value="client">
            {{ client.name }}
          </option>
        </select>
      </div>

      <!-- Sale Items -->
      <div v-for="(item, index) in sale.items" :key="index">
        <label for="product">Product:</label>
        <select v-model="item.inventoryItem" id="product" @change="updateTotalAmount" required>
          <option v-for="inv in inventory" :key="inv.id" :value="inv">
            {{ inv.product.name }}
          </option>
        </select>

        <label for="quantity">Quantity:</label>
        <input type="number" v-model="item.quantity" id="quantity" required min="1" @input="updateTotalAmount" />

        <button @click="removeItem(index)" type="button">Remove Item</button>
      </div>

      <button @click="addItem" type="button">Add Item</button>

      <!-- Total Amount Section -->
      <div>
        <label>Total Amount:</label>
        <span>{{ totalAmount }}</span>
      </div>

      <!-- Submit Button (Disabled if form is incomplete) -->
      <button type="submit" :disabled="!isFormValid">Submit Sale</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      store: null,
      sale: {
        date: null,  // Added date property
        client: null,
        totalAmount: 0,
        items: [
          {
            inventoryItem: null,
            quantity: 1,
          },
        ],
      },
      stores: [],
      clients: [],
      inventory: [],
    };
  },
  methods: {
    async submitForm() {
      try {
        for (const item of this.sale.items) {
          if(item.inventoryItem.quantity < item.quantity) {
            alert('Not enough stock for ' + item.inventoryItem.product.name);
            return;
          }
        }
        const response = await axios.post('http://localhost:8081/api/sales/add', this.sale, {
          headers: {
            'Content-Type': 'application/json',
          },
        });
        alert('Sale added successfully!');
      } catch (error) {
        alert(error.message);
      }
    },

    addItem() {
      this.sale.items.push({
        inventoryItem: null,  // Correctly use `inventoryItem` instead of `product`
        quantity: 1,
      });
    },

    removeItem(index) {
      this.sale.items.splice(index, 1);
      this.updateTotalAmount();  // Recalculate total after removing item
    },

    async fetchProductsByStore() {
      if (this.store && this.store.id) {
        try {
          const response = await axios.get(`http://localhost:8081/api/inventoryItems/byStore/${this.store.id}`);
          this.inventory = response.data;
        } catch (error) {
          console.error('Error fetching products:', error);
        }
      }
    },

    updateTotalAmount() {
      this.sale.totalAmount = this.totalAmount; 
    },
  },
  computed: {
    isFormValid() {
      // Check if all required fields are filled and the sale items are valid
      return (
        this.sale.date &&
        this.store &&
        this.sale.client &&
        this.sale.items.length > 0 &&
        this.sale.items.every(item => item.inventoryItem && item.quantity > 0)
      );
    },

    totalAmount() {
      // Calculate total amount for the sale
      return this.sale.items.reduce((total, item) => {
        if (item.inventoryItem && item.quantity > 0) {
          return total + item.inventoryItem.product.price * item.quantity;  // Use `inventoryItem.product` correctly
        }
        return total;
      }, 0).toFixed(2);  // Ensuring the total amount has two decimal places
    },
  },
  async created() {
    try {
      const [storesResponse, clientsResponse] = await Promise.all([
        axios.get('http://localhost:8081/api/stores/all'),
        axios.get('http://localhost:8081/api/clients/all'),
      ]);
      console.log(storesResponse.data);
      
      this.stores = storesResponse.data;
      this.clients = clientsResponse.data;
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  },
};
</script>
