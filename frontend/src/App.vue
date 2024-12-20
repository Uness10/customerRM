<template>
  <div id="app">
    <!-- Show Sidebar if authenticated -->
    <Sidebar v-if="isAuthenticated" />

    <!-- Show Home view if not authenticated -->
    <Home v-else />

    <!-- Main content area -->
    <router-view />
    <Footer />
  </div>
</template>

<script>
import Sidebar from './components/sidebar.vue';
import Footer from './components/footer.vue';
import Home from './views/home.vue';

export default {
  name: 'App',
  data() {
    return {
      isAuthenticated: !!localStorage.getItem('adminId'), // Check for token in localStorage
    };
  },
  watch: {
    // React to changes in authentication status
    $route() {
      this.isAuthenticated = !!localStorage.getItem('adminId');
    },
  },
  components: {
    Sidebar,
    Footer,
    Home,
  },
};
</script>

<style>



</style>
