<template>
  <div class="add-customer">
    <h1 class="text-2xl font-bold mb-4">Add New Customer</h1>
    <form @submit.prevent="submitCustomer">
      <div class="form-group">
        <label for="name">Name</label>
        <input
          id="name"
          type="text"
          v-model="customer.name"
          placeholder="Enter customer name"
          required
        />
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input
          id="email"
          type="email"
          v-model="customer.email"
          placeholder="Enter customer email"
          required
        />
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input
          id="phone"
          type="tel"
          v-model="customer.phone"
          placeholder="Enter customer phone"
          required
        />
      </div>
      <button type="submit" class="btn">Add Customer</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "AddCustomer",
  data() {
    return {
      customer: {
        name: "",
        email: "",
        phone: "",
      },
    };
  },
  methods: {
    submitCustomer() {
      // Send customer data to backend
      fetch("http://localhost:8080/api/customers", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.customer),
      })
        .then((response) => {
          if (!response.ok) throw new Error("Failed to add customer");
          return response.json();
        })
        .then(() => {
          alert("Customer added successfully!");
        })
        .catch((error) => alert(error.message));
    },
  },
};
</script>

<style scoped>
/* Add your styles here */
</style>
