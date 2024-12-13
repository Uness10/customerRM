<template>
  <div class="dashboard">
    <!-- Welcome Again Section -->
    <div class="welcome-section bg-blue-500 text-white p-6 rounded-lg mb-6">
      <h1 class="text-2xl font-bold">Welcome Again, {{ userName }}!</h1>
      <p class="mt-2 text-lg">We're glad to see you back. Let's make your day productive!</p>
    </div>

    <!-- Quick Actions Section -->
    <QuickActions />
  </div>
</template>

<script>
import QuickActions from "@/components/QuickActions.vue";

export default {
  name: "Dashboard",
  components: {
    QuickActions,
  },
  data() {
    return {
      userName: "Youness", // Replace with dynamic data as needed
    };
  },
};
</script>

<style scoped>
.dashboard {
  padding: 2rem;
  background-color: #f4f4f9;
  min-height: 100vh;
}
.welcome-section {
  background-color: #1e90ff; /* Change to match your theme */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>
