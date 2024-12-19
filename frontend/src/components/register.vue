<template>
    <form
      class="bg-white dark:bg-zinc-900 shadow-xl rounded-xl overflow-hidden border-4 custom-form" 
      @submit.prevent="register"
    >
      <div class="px-5 py-5">
        <h2 class="text-3xl font-extrabold text-center text-zinc-800 dark:text-white">
          Welcome !
        </h2>
        <p class="text-center text-zinc-600 dark:text-zinc-400 mt-2">
          Create your account.
        </p>
        <div class="mt-6">
          <!-- Username Field -->
          <div class="relative">
            <label
              class="block mb-2 text-sm font-medium text-zinc-600 dark:text-zinc-200"
              for="username"
            >Username</label>
            <input
              v-model="username" 
              placeholder="username"
              class="block w-full px-4 py-2 text-zinc-800 bg-white border-2 rounded-md dark:border-zinc-600 dark:bg-zinc-800 dark:text-zinc-200 focus:border-blue-500 dark:focus:border-blue-400 focus:ring-opacity-50 focus:outline-none focus:ring focus:ring-blue-400"
              name="username"
              id="username"
              type="text"
              required
            />
          </div>
  
          <!-- Password Field -->
          <div class="mt-4">
            <label
              class="block mb-2 text-sm font-medium text-zinc-600 dark:text-zinc-200"
              for="password"
            >Password</label>
            <input
              v-model="password" 
              placeholder="••••••••"
              class="block w-full px-4 py-2 text-zinc-800 bg-white border-2 rounded-md dark:border-zinc-600 dark:bg-zinc-800 dark:text-zinc-200 focus:border-blue-500 dark:focus:border-blue-400 focus:ring-opacity-50 focus:outline-none focus:ring focus:ring-blue-400"
              name="password"
              id="password"
              type="password"
              required
            />
          </div>
  

          <!-- Submit Button -->
          <div class="mt-6">
            <button
              class="w-full px-4 py-2 text-white transition-colors duration-200 transform bg-gradient-to-r from-blue-600 to-cyan-600 rounded-md hover:from-blue-700 hover:to-cyan-700 focus:outline-none focus:ring-4 focus:ring-blue-400 dark:focus:ring-blue-800"
              type="submit"
            >
              Let's Go
            </button>
          </div>
                    <!-- Error Message -->
                    <div v-if="errorMessage" class="mt-4 text-red-600 text-center">
            {{ errorMessage }}
          </div>
  
        </div>
      </div>
    </form>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        username: '',  // Bind to input field
        password: '',  // Bind to input field
        errorMessage: null, // Will hold error messages
      };
    },
    methods: {
      async register() {
        try {
          const req = {
            username: this.username,
            password: this.password,
          };
  
          // Send register credentials as an Admin object in the request body
          const response = await axios.post('http://localhost:8081/auth/register', req);
  
          const token = response.data.replace('Bearer ', ''); // Remove the "Bearer " prefix
        localStorage.setItem('authToken', token);

        // Redirect to dashboard after successful login
        this.$router.push('/dashboard');

        } catch (error) {
          // Handle errors that occur during the registration process
          if (error.response) {
            this.errorMessage = error.response.data || 'An error occurred during registration';
          } else {
            this.errorMessage = 'Unable to connect to the server';
          }
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .custom-form {
    padding: 1rem;
    width: 100%;

    margin-top: 10vh;
    border-radius: 12px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }
  </style>
  