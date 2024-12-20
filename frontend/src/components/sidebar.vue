<template>
  <div :class="['sidebar', { folded: isFolded }]">
    <div class="sidebar-header">
      <h2 v-if="!isFolded">CRM</h2>
      <button 
        class="toggle-btn" 
        @click="toggleSidebar"
        aria-label="Toggle sidebar"
      >
        <span v-if="isFolded">☰</span>
        <span v-else>×</span>
      </button>
    </div>
    <ul class="sidebar-links">
      <li v-for="link in links" :key="link.route" class="sidebar-item">
        <router-link :to="link.route" class="sidebar-link">
          <span :class="link.icon" class="icon"></span>
          <span v-show="!isFolded" class="label">{{ link.label }}</span>
        </router-link>
      </li>
      <!-- Logout Button -->
      <li class="sidebar-item">
        <button 
          @click="logout" 
          class="sidebar-link logout-btn"
        >
          <span class="fas fa-sign-out-alt icon"></span>
          <span v-show="!isFolded" class="label">Logout</span>
        </button>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      isFolded: true,
      links: [
        { route: '/dashboard', label: 'Dashboard', icon: 'fas fa-chart-bar' },
        { route: '/sales', label: 'Sales', icon: 'fas fa-dollar-sign' },
        { route: '/customers', label: 'Customers', icon: 'fas fa-users' },
        { route: '/inventory', label: 'Inventory', icon: 'fas fa-box' },
        { route: '/settings', label: 'Settings', icon: 'fas fa-cogs' },
      ],
    };
  },
  methods: {
    toggleSidebar() {
      this.isFolded = !this.isFolded;
    },
    logout() {
      // Remove token from localStorage
      localStorage.removeItem('adminId');
      
      // Redirect to the login page
      this.$router.push('/');

      // Optionally emit an event or update global state
      this.$emit('logout');
    },
  },
};
</script>

<style scoped>
.sidebar {

  width: 20%;
  height: 100vh;
  background-color: #333;
  color: white;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  flex-direction: column;
  transition: width 0.3s ease, background-color 0.3s ease;
  justify-content: flex-start;
  align-items: center;
  padding-top: 20px;
}

.sidebar.folded {
  width: 7%;
  z-index: 4;
  background-color: #444;
}

.sidebar-links {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
}

.sidebar-item {
  display: flex;
  justify-content: center;
  align-items: center;
}
.logout-btn {
  background: none;
  border: none;
  color: white;
  font-size: 16px;
  text-align: center;
  width: 100%;
  cursor: pointer;
  padding: 10px 15px;
  transition: background-color 0.3s ease;
}

.logout-btn:hover {
  background-color: #555;
}


.sidebar-link {
  display: flex;
  align-items: center;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  transition: background-color 0.3s ease;
  padding: 10px 15px;
  width: 100%;
  text-align: center;
}

.sidebar-link:hover {
  background-color: #555;
}

.sidebar-link .icon {
  font-size: 24px;
  margin-bottom: 5px;
}

.sidebar.folded .label {
  display: none;
}

.sidebar:not(.folded) .icon {
  display: none;
}

.toggle-btn {
  background: none;
  border: none;
  color: white;
  font-size: 24px;
  cursor: pointer;
  padding: 10px;
  transition: transform 0.3s;
}

.toggle-btn:hover {
  transform: scale(1.1);
}
</style>
