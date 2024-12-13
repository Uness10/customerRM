<template>
    <div>
      <h2>Create a New Sale</h2>
      <form @submit.prevent="submitForm">
        <!-- Store Selection -->
        <div>
          <label for="store">Store:</label>
          <select v-model="sale.storeId" id="store" required>
            <option v-for="store in stores" :key="store.storeId" :value="store.storeId">
              {{ store.name }}
            </option>
          </select>
        </div>
  
        <!-- Client Selection -->
        <div>
          <label for="client">Client:</label>
          <select v-model="sale.clientId" id="client" required>
            <option v-for="client in clients" :key="client.clientId" :value="client.clientId">
              {{ client.name }}
            </option>
          </select>
        </div>
  
        <!-- Sale Items -->
        <div v-for="(item, index) in sale.items" :key="index">
          <label for="product">Product:</label>
          <select v-model="item.productId" id="product" required>
            <option v-for="product in products" :key="product.productId" :value="product.productId">
              {{ product.name }}
            </option>
          </select>
  
          <label for="quantity">Quantity:</label>
          <input type="number" v-model="item.quantity" id="quantity" required min="1" />
  
          <button @click="removeItem(index)" type="button">Remove Item</button>
        </div>
  
        <button @click="addItem" type="button">Add Item</button>
  
        <button type="submit">Submit Sale</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        sale: {
          storeId: null,  // Store ID to be selected
          clientId: null, // Client ID to be selected
          items: [
            {
              productId: null, // Product ID
              quantity: 1,     // Quantity
            },
          ],
        },
        stores: [],  // List of stores
        clients: [], // List of clients
        products: [], // List of products
      };
    },
    methods: {
      submitForm() {
        axios
          .post('/api/sales', this.sale)
          .then((response) => {
            console.log('Sale created successfully:', response.data);
            alert('Sale Created Successfully');
          })
          .catch((error) => {
            console.error('There was an error creating the sale:', error);
          });
      },
  
      addItem() {
        this.sale.items.push({
          productId: null,
          quantity: 1,
        });
      },
  
      removeItem(index) {
        this.sale.items.splice(index, 1);
      },
    },
    created() {
      // Fetch stores, clients, and products from the backend to populate the dropdowns
      axios
        .get('/api/stores')
        .then((response) => {
          this.stores = response.data;
        })
        .catch((error) => {
          console.error('Error fetching stores:', error);
        });
  
      axios
        .get('/api/clients')
        .then((response) => {
          this.clients = response.data;
        })
        .catch((error) => {
          console.error('Error fetching clients:', error);
        });
  
      axios
        .get('/api/products')
        .then((response) => {
          this.products = response.data;
        })
        .catch((error) => {
          console.error('Error fetching products:', error);
        });
    },
  };
  </script>
  