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
        sale: {
          storeId: null,
          clientId: null,
          items: [
            {
              productId: null,
              quantity: 1,
            },
          ],
        },
        stores: [],
        clients: [],
        products: [],
      };
    },
    methods: {
      async submitForm() {
        try {
          const response = await axios.post('http://localhost:8081/api/sales', this.sale);
          console.log('Sale created successfully:', response.data);
          alert('Sale Created Successfully');
        } catch (error) {
          console.error('There was an error creating the sale:', error);
        }
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
    computed: {
      isFormValid() {
        // Check if all required fields are filled and the sale items are valid
        return (
          this.sale.storeId &&
          this.sale.clientId &&
          this.sale.items.every(item => item.productId && item.quantity > 0)
        );
      },
    },
    async created() {
      try {
        const [storesResponse, clientsResponse, productsResponse] = await Promise.all([
         axios.get('http://localhost:8081/api/stores/all'),
          axios.get('http://localhost:8081/api/clients/all'),
          axios.get('http://localhost:8081/api/products/all'),
        ]);
        
        this.stores = storesResponse.data;
        this.clients = clientsResponse.data;
        this.products = productsResponse.data;
        
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
  };
  </script>
  