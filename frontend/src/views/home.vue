<template>
  <div class="home-container">
    <!-- Navbar -->
    <nav class="navbar">
      <router-link to="/" class="nav-item">Home</router-link>
      <router-link to="/about" class="nav-item">About</router-link>
      <router-link to="/contact" class="nav-item">Contact</router-link>
      <button v-if="isAuthenticated" @click="logout" class="nav-item logout-btn">Logout</button>
    </nav>

    <!-- Main Content -->
    <main class="main-content">
      <!-- Login Section -->
      <section class="login-section">
        <Login v-if="toggle"/>
        <Register v-else/>
        <div class="px-8 py-3 bg-blue-200 dark:bg-zinc-800 text-center ">
        <div class="text-sm text-blue-900 dark:text-blue-300" v-if="toggle">
          Don't have an account?
          <div class="font-medium underline text-blue-600 hover:text-blue-700 dark:text-blue-400 dark:hover:text-blue-500" @click="toggleForm">
            Register
          </div>
        </div>
        <div class="text-sm text-blue-900 dark:text-blue-300" v-else>
          Already have an account?
          <div class="font-medium underline text-blue-600 hover:text-blue-700 dark:text-blue-400 dark:hover:text-blue-500" @click="toggleForm">
            Login
          </div>
        </div>
      </div>
      </section>

      <!-- Footer Section with Toggle -->

    </main>
  </div>
</template>

<script>
import Login from "@/components/login.vue";
import Register from "@/components/register.vue";

export default {
  name: "Home",
  components: {
    Login,
    Register
  },
  data() {
    return {
      toggle: true, // Initially show the login form
      isAuthenticated: false // Add this if you have an auth system
    };
  },
  methods: {
    toggleForm() {
      this.toggle = !this.toggle; // Toggle between login and register forms
    },
    logout() {
      // Handle logout logic here
      this.isAuthenticated = false;
      this.$router.push('/'); // Redirect to home after logout
    }
  }
};
</script>

<style scoped>
/* General Layout */
.home-container {
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background-color: transparent;
  background-image: radial-gradient(#1e40af 1px, #f3f3f3 1px);
  background-size: 50px 50px;
  width: 100%;
  height: 100%;
}

/* Navbar Styles */
.navbar {
  display: flex;
  justify-content: space-around;
  align-items: center;
  background-color: #1e64af; /* Darker shade for navbar */
  color: white;
  padding: 1rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.nav-item {
  color: white;
  text-decoration: none;
  font-size: 1.2rem;
  margin: 0 1rem;
  transition: color 0.3s ease;
}

.nav-item:hover {
  color: #42b983; /* Green hover effect for navbar */
}

.main-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: calc(100vh - 80px); /* Adjust height for navbar */
}

.login-section {
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center; /* Centers content horizontally */
  justify-content: center; /* Centers content vertically */
  width: 30%; /* Adjust the width as needed */
}

</style>
