<template>
  <div :class="['sidebar', { folded: isFolded }]">
    <div class="sidebar-header">
      <h2 v-if="!isFolded">My App</h2>
      <button class="toggle-btn" @click="toggleSidebar">
        <span v-if="isFolded">☰</span>
        <span v-else>×</span>
      </button>
    </div>
    <ul class="sidebar-links">
      <li v-for="link in links" :key="link.route" class="sidebar-item">
        <router-link :to="link.route" class="sidebar-link">
          <span class="icon">{{ link.icon }}</span>
          <span v-show="!isFolded" class="label">{{ link.label }}</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>


  
<script>
export default {
  name: 'Sidebar',
  data() {
    return {
      isFolded: true, // Controls whether the sidebar is folded
      links: [
        { route: '/dashboard', label: 'Dashboard', icon: '📊' },
        { route: '/sales', label: 'Sales', icon: '💰' },
        { route: '/customers', label: 'Customers', icon: '👥' },
        { route: '/inventory', label: 'Inventory', icon: '📦' },
        { route: '/settings', label: 'Settings', icon: '⚙️' },
      ],
    };
  },
  methods: {
    toggleSidebar() {
      this.isFolded = !this.isFolded;
    },
  },
};
</script>

<style scoped>
.sidebar {
  width: 250px;
  height: 100vh;
  background-color: #333;
  color: white;
  padding: 20px;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  flex-direction: column;
  transition: width 0.3s ease-in-out;
}

.sidebar.folded {
  width: 3%; /* When folded, set a smaller width */
}

.sidebar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 30px;
}

.sidebar-header h2 {
  font-size: 24px;
  margin: 0;
  transition: opacity 0.3s ease-in-out;
}

.toggle-btn {
  background: none;
  border: none;
  color: white;
  font-size: 30px;
  cursor: pointer;
  padding: 5px;
}

.sidebar-links {
  list-style-type: none;
  padding: 0;
  margin-top: 10px;
}

.sidebar-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.sidebar-link {
  display: flex;
  align-items: center;
  color: white;
  text-decoration: none;
  padding: 10px;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.sidebar-link:hover {
  background-color: #555;
}

.sidebar-link .icon {
  font-size: 20px;
  margin : 1% 1%;

}

.sidebar.folded .label {
  display: none; /* Hide labels when folded */
}
</style>
