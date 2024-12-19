<template>
  <sidebar v-if="isAuthenticated"/>
  <nav v-else>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link> |
    <router-link to="/contact">Contact</router-link>
  </nav>
    <router-view/>

<Footer/>

</template>

<script>
import sidebar from './components/sidebar.vue'
import Footer from './components/footer.vue'
export default {
  data() {
    return {
      isAuthenticated: true // Set this to true or false based on your authentication logic
    };
  },
  components: {
    sidebar,
    Footer
  }
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
