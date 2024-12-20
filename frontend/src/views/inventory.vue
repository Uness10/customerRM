<template>
    <div class="inventory-view">
      <h1>Inventory Management</h1>
      <InventoryByStore />
      <manageProducts/>
        <manageStore/>
    </div>

  </template>
  
  <script>
  import InventoryByStore from "../components/inventoryByStore.vue";

  import manageProducts from "@/components/manageProducts.vue";
  import manageStore from "@/components/manageStore.vue";
  export default {
    name : "Inventory",
    components: {
      InventoryByStore,
      manageProducts,
      manageStore

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
  