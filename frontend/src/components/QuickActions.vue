<template>
  <div class="quick-actions">
    <!-- Collapse/Expand Button -->
    <button class="collapse-btn" @click="toggleCollapse">
      {{ isCollapsed ? 'Expand Quick Actions' : 'Collapse Quick Actions' }}
    </button>

    <!-- Actions Section (conditionally rendered) -->
    <div v-show="!isCollapsed" class="actions-content">
      <h3 class="section-title">Quick Actions</h3>
      <div class="actions-container">
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
      </div>
    </div>
  </div>
</template>

<script>
import RecordSale from "@/components/RecordSale.vue";
import AddCustomer from "@/components/AddCustomer.vue";

export default {
  name: "QuickActions",
  data() {
    return {
      isCollapsed: false, // Controls the collapsed state
      c1: false,
      c2: false,
    };
  },
  methods: {
    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed;
    },
    toggleComponent(componentKey) {
      this[componentKey] = !this[componentKey];
    },
  },
  components: {
    RecordSale,
    AddCustomer,
  },
};
</script>

<style scoped>
/* Quick Actions Container */
.quick-actions {
  background-color: #f9fafb;
  padding: 1.5rem;
  border-radius: 1rem;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  margin-top: 2rem;
  text-align: center;
}

/* Collapse Button */
.collapse-btn {
  background-color: #3b82f6;
  color: white;
  border: none;
  border-radius: 0.75rem;
  font-size: 1rem;
  font-weight: 600;
  padding: 0.75rem 1.5rem;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-bottom: 1rem;
}

.collapse-btn:hover {
  background-color: #2563eb;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
}

.collapse-btn:active {
  background-color: #1e3a8a;
}

/* Actions Content */
.actions-content {
  overflow: hidden;
  transition: max-height 0.3s ease, opacity 0.3s ease;
}

.actions-content[style*="display: none"] {
  max-height: 0;
  opacity: 0;
}

/* Section Title */
.section-title {
  font-size: 1.75rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 1.5rem;
}

/* Actions Container */
.actions-container {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  justify-items: center;
  align-items: center;
}

/* Action Button Styling */
.action-btn {
  background-color: #3b82f6;
  color: #ffffff;
  border: none;
  border-radius: 0.75rem;
  font-size: 1rem;
  font-weight: 600;
  padding: 1rem 1.5rem;
  cursor: pointer;
  text-align: center;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
  width: 70%;
}

.action-btn:hover {
  background-color: #2563eb;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.1);
  transform: translateY(-2px);
}

.action-btn:active {
  background-color: #1e3a8a;
  transform: translateY(1px);
}

/* Responsive Design */
@media (max-width: 768px) {
  .section-title {
    font-size: 1.5rem;
  }

  .actions-container {
    flex-direction: column;
    gap: 1rem;
  }

  .action-btn {
    width: 100%;
  }
}
</style>
