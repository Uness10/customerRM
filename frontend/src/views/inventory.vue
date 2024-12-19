<template>
    <div class="inventory-view">
      <h1>Inventory Management</h1>
      <InventoryByStore />

    </div>
  </template>
  
  <script>
  import InventoryByStore from "../components/inventoryByStore.vue";

  
  export default {
    name : "Inventory",
    components: {
      InventoryByStore,

    },
  };
  </script>
  
  <style scoped>
  .inventory-view {
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin-left: 10%;
  }
  </style>
  