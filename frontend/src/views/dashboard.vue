<template>
  <div class="dashboard">
    <!-- Welcome Again Section -->
    <section class="bg-gradient-to-r from-blue-600 to-blue-400 text-white p-8 rounded-xl shadow-lg text-center mb-2">

      <h1 class="text-3xl font-extrabold mb-2">Welcome Back, {{ username }}!</h1>
      <p class="text-lg mb-6">We're thrilled to see you again. Let's make today productive and amazing!</p>
    </section>

    <!-- Quick Actions Section -->
    <Numbers />

    <!-- Additional Features Section -->
    <section class="mt-8">
      <h2 class="text-2xl font-semibold text-gray-800 text-center mb-6">Explore More Features</h2>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <div
          v-for="feature in features"
          :key="feature.id"
          class="bg-white p-6 rounded-lg shadow-lg hover:transform hover:scale-105 transition-transform duration-300 ease-in-out"
        >
          <h3 class="text-xl font-semibold text-blue-600 mb-3">{{ feature.title }}</h3>
          <p class="text-gray-700 mb-4">{{ feature.description }}</p>
          <button
            class="bg-blue-600 text-white py-2 px-6 rounded-lg hover:bg-blue-500 transition-all"
            @click="feature.action"
          >
            Learn More
          </button>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from 'axios';
import Numbers from "@/components/numbers.vue";

export default {
  name: "Dashboard",
  components: {
    Numbers,
  },
  data() {
    return {
      username: null, // Replace with dynamic data as needed
      features: [
        {
          id: 1,
          title: "Analytics",
          description: "Get detailed insights into your activity and progress.",
          action: this.navigateToAnalytics, // Use method references here
        },
        {
          id: 2,
          title: "Settings",
          description: "Customize your preferences.",
          action: this.navigateToSettings,
        },
        {
          id: 3,
          title: "Support",
          description: "Reach out to our support team for help and assistance.",
          action: this.navigateToSupport,
        },
      ],
    };
  },
  methods: {
    async getUsername() {
      try {
        const adminId = localStorage.getItem("adminId");
        if (adminId) {
          const response = await axios.get(`http://localhost:8081/api/admin/${adminId}`);
          this.username = response.data; // Adjust based on API response
          console.log(this.username)
        } else {
          console.error("Admin ID not found in localStorage.");
        }
      } catch (error) {
        console.error("Error fetching username:", error);
      }
    },
    navigateToAnalytics() {
      this.$router.push("/analytics");
    },
    navigateToSettings() {
      this.$router.push("/settings");
    },
    navigateToSupport() {
      this.$router.push("/support");
    },
  },
  created() {
    this.getUsername();
  },
};
</script>

<style scoped>
.dashboard {
  padding: 2rem;
  min-height: 100vh;
  font-family: 'Inter', sans-serif;
  margin-left: 10%;
}

/* Welcome Section */
.welcome-section {
  background: linear-gradient(to right, #2563eb, #1d4ed8);
  padding: 2rem;
  border-radius: 1rem;
  text-align: center;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
}

/* Additional Features Section */
.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
}
</style>
