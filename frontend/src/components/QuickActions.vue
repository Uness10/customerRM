<template>
  <div class="quick-actions">
    <h3 class="text-lg font-bold mb-4">Quick Actions</h3>
    <div class="actions-container grid grid-cols-3 gap-4">
      <!-- Record Sale Button -->
      <button class="action-btn" @click="toggleComponent('c1')" v-show="!c1">
        Record A Sale
      </button>
      <RecordSale v-if="c1" @close="toggleComponent('c1')" />

      <!-- Add Customer Button -->
      <button class="action-btn" @click="toggleComponent('c2')" v-show="!c2">
        Add A Customer
      </button>
      <AddCustomer v-if="c2" @close="toggleComponent('c2')" />

      <!-- Update Inventory Button -->
      <button class="action-btn" @click="toggleComponent('c3')" v-show="!c3">
        Update Inventory
      </button>
      <UpdateInventory v-if="c3" @close="toggleComponent('c3')" />
    </div>
  </div>
</template>

<script>
import RecordSale from "@/components/RecordSale.vue";
import AddCustomer from "@/components/AddCustomer.vue";
import UpdateInventory from "@/components/UpdateInventory.vue";

export default {
  name: "QuickActions",
  data() {
    return {
      c1: false,
      c2: false,
      c3: false,
    };
  },
  methods: {
    toggleComponent(componentKey) {
      // Toggles visibility of the respective component
      this[componentKey] = !this[componentKey];
    },
  },
  components: {
    RecordSale,
    AddCustomer,
    UpdateInventory,
  },
};
</script>

<style scoped>
.quick-actions {
  background-color: #f9f9f9;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.actions-container {
  display: flex;
  justify-content: space-between;
}
.action-btn {
  background-color: #4caf50;
  color: white;
  padding: 0.75rem 1rem;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  text-align: center;
  transition: background-color 0.3s ease;
}
.action-btn:hover {
  background-color: #45a049;
}
</style>
