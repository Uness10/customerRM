<template>
  <div class="dashboard">
    <!-- Welcome Again Section -->
    <section class="welcome-section">
      <h1 class="welcome-title">Welcome Back, {{ userName }}!</h1>
      <p class="welcome-message">We are thrilled to see you again. Let's make today productive and amazing!</p>
      <button class="welcome-btn" @click="handleMotivationClick">Get Motivation</button>
    </section>

    <!-- Quick Actions Section -->
    <QuickActions />

    <!-- Additional Features Section -->
    <section class="additional-features">
      <h2 class="features-title">Explore More Features</h2>
      <div class="features-grid">
        <div
          v-for="feature in features"
          :key="feature.id"
          class="feature-card"
        >
          <h3 class="feature-title">{{ feature.title }}</h3>
          <p class="feature-description">{{ feature.description }}</p>
          <button class="feature-btn" @click="feature.action">Learn More</button>
        </div>
      </div>
    </section>
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
      features: [
        {
          id: 1,
          title: "Analytics",
          description: "Get detailed insights into your activity and progress.",
          action: () => alert("Navigating to Analytics..."),
        },
        {
          id: 2,
          title: "Settings",
          description: "Customize your dashboard and preferences.",
          action: () => alert("Navigating to Settings..."),
        },
        {
          id: 3,
          title: "Support",
          description: "Reach out to our support team for help and assistance.",
          action: () => alert("Navigating to Support..."),
        },
      ],
    };
  },
  methods: {
    handleMotivationClick() {
      alert("You got this! Stay motivated and crush your goals today!");
    },
  },
};
</script>

<style scoped>
.dashboard {
  padding: 2rem;
  background-color: #f3f4f6;
  min-height: 100vh;
  margin-left: 5%;
  font-family: 'Inter', sans-serif;
}

/* Welcome Section */
.welcome-section {
  background: linear-gradient(to right, #232c3b, #2563eb);
  padding: 2rem;
  border-radius: 1rem;
  color: white;
  text-align: center;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
}

.welcome-section:hover {
  box-shadow: 0 12px 20px rgba(0, 0, 0, 0.15);
}

.welcome-title {
  font-size: 2.5rem;
  font-weight: 800;
  margin-bottom: 1rem;
}

.welcome-message {
  font-size: 1.25rem;
  line-height: 1.6;
  margin-bottom: 1.5rem;
}

.welcome-btn {
  padding: 0.75rem 2rem;
  font-size: 1rem;
  font-weight: 600;
  background-color: white;
  color: #2563eb;
  border: none;
  border-radius: 0.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
}

.welcome-btn:hover {
  background-color: #e0e7ff;
  transform: scale(1.05);
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
}

/* Additional Features */
.additional-features {
  margin-top: 2rem;
  padding: 1.5rem 0;
}

.features-title {
  font-size: 2rem;
  font-weight: 700;
  color: #1f2937;
  text-align: center;
  margin-bottom: 2rem;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 1.5rem;
}

/* Feature Card */
.feature-card {
  background-color: white;
  border-radius: 1rem;
  padding: 1.5rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.feature-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
}

.feature-title {
  font-size: 1.5rem;
  font-weight: 600;
  color: #3b82f6;
  margin-bottom: 0.75rem;
}

.feature-description {
  font-size: 1rem;
  color: #4b5563;
  margin-bottom: 1.5rem;
}

.feature-btn {
  padding: 0.5rem 1.5rem;
  font-size: 0.875rem;
  font-weight: 600;
  background-color: #3b82f6;
  color: white;
  border: none;
  border-radius: 0.5rem;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.feature-btn:hover {
  background-color: #2563eb;
  transform: scale(1.05);
}

@media (max-width: 768px) {
  .dashboard {
    margin-left: 0;
  }
}
</style>
