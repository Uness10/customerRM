<template>
    <div class="p-6 bg-white rounded-lg shadow-md">
      <h1 class="text-2xl font-semibold mb-4 text-gray-800">
        {{ isEditing ? "Edit Product" : "Add New Product" }}
      </h1>
  
      <form @submit.prevent="submitForm" class="space-y-4">
        <!-- Product Name -->
        <div>
          <label for="name" class="block text-sm font-medium text-gray-700">Product Name</label>
          <input
            type="text"
            v-model="product.name"
            id="name"
            placeholder="Enter Product Name"
            required
            class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
        </div>
  
        <!-- Product Price -->
        <div>
          <label for="price" class="block text-sm font-medium text-gray-700">Price</label>
          <input
            type="number"
            v-model="product.price"
            id="price"
            placeholder="Enter Product Price"
            required
            class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          />
        </div>
  
        <!-- Buttons -->
        <div class="flex space-x-4">
          <button
            type="submit"
            class="px-6 py-2 bg-green-600 text-white font-medium rounded-md hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-green-500"
          >
            {{ isEditing ? "Update Product" : "Add Product" }}
          </button>
          <button
            type="button"
            class="px-6 py-2 bg-gray-300 text-gray-800 font-medium rounded-md hover:bg-gray-400 focus:outline-none focus:ring-2 focus:ring-gray-400"
            @click="closeForm"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        product: {
          name: "",
          price: 0,
        },
        isEditing: false,
      };
    },
    props: {
      productId: {
        type: Number,
        required: false,
      },
    },
    methods: {
      submitForm() {
        if (this.isEditing) {
          axios
            .put("http://localhost:8081/api/products/update", this.product)
            .then(() => {
              this.$emit("close"); // Close the form
              this.$router.push("/product"); // Redirect to the products list
            });
        } else {
          axios
            .post("http://localhost:8081/api/products/add", this.product)
            .then(() => {
              this.$emit("close"); // Close the form
              location.reload();
            });
        }
      },
      closeForm() {
        this.$emit("close"); // Close the form when the user clicks cancel
      },
    },
    created() {
      if (this.productId) {
        this.isEditing = true;
        axios
          .get(`http://localhost:8081/api/products/${this.productId}`)
          .then((response) => {
            this.product = response.data;
          });
      }
    },
  };
  </script>
  
  <style scoped>
  /* Add custom styles here if necessary, but Tailwind handles most styles */
  </style>
  