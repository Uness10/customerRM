<template>
   <div class="add-customer-form">
    <h1 class="section-title">Add New Customer</h1>
    <form @submit.prevent="submitCustomer" class="form">
      <!-- Name Input -->
      <div class="form-group">
        <label for="name">Name</label>
        <input
          id="name"
          type="text"
          v-model="customer.name"
          placeholder="Enter customer name"
          required
          class="form-input"
        />
      </div>

      <!-- Contact Info Input -->
      <div class="form-group">
        <label for="email">Contact Info</label>
        <input
          id="email"
          type="email"
          v-model="customer.contactInfo"
          placeholder="Enter customer contact info"
          required
          class="form-input"
        />
      </div>

      <!-- Submit Button -->
      <button type="submit" class="submit-btn">Add Customer</button>
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
      contactInfo: "",
    },
  };
},
methods: {
  async submitCustomer() {
    try {
      const response = await fetch("http://localhost:8081/api/clients/add", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.customer),
      });

      if (!response.ok) throw new Error("Failed to add customer");

      const result = await response.json();
      alert("Customer added successfully!");
      this.customer = { name: "", contactInfo: "" }; // Reset form
    } catch (error) {
      alert(error.message);
    }
  },
},
};
</script>
<style scoped>

.sale-form-container {
  background-color: #ffffff;
  padding: 2.5rem;
  border-radius: 1rem;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin: 3rem auto;
  font-family: 'Inter', sans-serif;
}

.section-title {
  font-size: 2rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 2rem;
  text-align: center;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 1rem;
  font-weight: 600;
  color: #374151;
  margin-bottom: 0.5rem;
}

.form-input,
.form-select {
  padding: 0.9rem;
  font-size: 1rem;
  border-radius: 0.75rem;
  border: 1px solid #d1d5db;
  width: 100%;
}

.form-input:focus,
.form-select:focus {
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.3);
  outline: none;
}

button {
  background-color: #3b82f6;
  color: white;
  padding: 0.9rem 1.5rem;
  border: none;
  border-radius: 0.75rem;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
  width: 100%;
  text-align: center;
}

button:hover {
  background-color: #2563eb;
  transform: translateY(-2px);
}

button:active {
  background-color: #1e40af;
  transform: translateY(1px);
}

.remove-item-btn {
  background-color: #ef4444;
  margin-top: 0.5rem;
}

.add-item-btn {
  background-color: #22c55e;
}

.total-amount {
  font-size: 1.25rem;
  font-weight: 700;
  margin-top: 1rem;
  color: #333;
}

.amount {
  font-size: 1.5rem;
  color: #4caf50;
}

</style>