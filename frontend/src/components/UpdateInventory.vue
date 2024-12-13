<template>
  <div class="update-inventory">
    <h1 class="text-2xl font-bold mb-4">Update Inventory</h1>
    <form @submit.prevent="updateItem">
      <div class="form-group">
        <label for="product">Product Name</label>
        <input
          id="product"
          type="text"
          v-model="inventory.product"
          placeholder="Enter product name"
          required
        />
      </div>
      <div class="form-group">
        <label for="quantity">New Quantity</label>
        <input
          id="quantity"
          type="number"
          v-model="inventory.quantity"
          min="1"
          required
        />
      </div>
      <button type="submit" class="btn">Update Inventory</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "UpdateInventory",
  data() {
    return {
      inventory: {
        product: "",
        quantity: 0,
      },
    };
  },
  methods: {
    updateItem() {
      // Send inventory data to backend
      fetch("http://localhost:8080/api/inventory", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.inventory),
      })
        .then((response) => {
          if (!response.ok) throw new Error("Failed to update inventory");
          return response.json();
        })
        .then(() => {
          alert("Inventory updated successfully!");
        })
        .catch((error) => alert(error.message));
    },
  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
