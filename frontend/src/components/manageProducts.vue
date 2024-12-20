<template>
    <div class="products p-6 bg-gray-50 mr-8">
      <h1 class="text-2xl font-bold mb-6">Products</h1>
      <button
        class="px-4 py-2 mb-6 bg-blue-500 text-white rounded shadow hover:bg-blue-700"
        @click="openAddForm"
      >
        Add New Product
      </button>
  
      <table class="products-table w-full border-collapse bg-white shadow rounded">
        <thead>
          <tr class="bg-gray-100 text-left">
            <th class="px-4 py-2 border-b border-gray-300">ID</th>
            <th class="px-4 py-2 border-b border-gray-300">Name</th>
            <th class="px-4 py-2 border-b border-gray-300">Price</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="product in products"
            :key="product.id"
            class="hover:bg-gray-50"
          >
            <td class="px-4 py-2 border-b border-gray-300">{{ product.id }}</td>
            <td class="px-4 py-2 border-b border-gray-300">{{ product.name }}</td>
            <td class="px-4 py-2 border-b border-gray-300">
              ${{ product.price }}
            </td>
          </tr>
        </tbody>
      </table>
  
      <!-- Add/Edit Product Form -->
      <AddProductForm
        v-if="showForm"
        :productId="editingProductId"
        @close="closeForm"
      />
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import AddProductForm from "./addProduct.vue";
  
  export default {
    name: "manageProducts",
    components: {
      AddProductForm,
    },
    data() {
      return {
        products: [], // List of products
        showForm: false, // Controls whether the form is displayed
        editingProductId: null, // ID of the product being edited
      };
    },
    methods: {
      fetchProducts() {
        axios
          .get("http://localhost:8081/api/products/all")
          .then((response) => {
            this.products = response.data;
          })
          .catch((error) => {
            console.error("Error fetching products:", error);
          });
      },
      openAddForm() {
        this.editingProductId = null; // Reset editing ID for new product
        this.showForm = true; // Show the form
      },
      editProduct(productId) {
        this.editingProductId = productId; // Set the product ID for editing
        this.showForm = true; // Show the form
      },
      deleteProduct(productId) {
        axios
          .delete(`http://localhost:8081/api/products/delete/${productId}`)
          .then(() => {
            this.fetchProducts(); // Refresh product list
          })
          .catch((error) => {
            console.error("Error deleting product:", error);
          });
      },
      closeForm() {
        this.showForm = false; // Hide the form
        this.fetchProducts(); // Refresh product list
      },
    },
    created() {
      this.fetchProducts(); // Load products when the component is created
    },
  };
  </script>
  
  <style scoped>
  .products {
    @apply p-6 bg-gray-50;
  }
  
  .products-table th,
  .products-table td {
    @apply px-4 py-2 border-b border-gray-300;
  }
  
  .products-table th {
    @apply bg-gray-100 text-left;
  }
  
  button {
    @apply px-4 py-2 rounded shadow text-white cursor-pointer;
  }
  
  button:hover {
    @apply bg-opacity-80;
  }
  </style>
  