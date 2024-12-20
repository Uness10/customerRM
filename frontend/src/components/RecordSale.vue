<template>
  <div class="sale-form-container">
    <h1 class="section-title">Create a New Sale</h1>
    <form @submit.prevent="submitForm" class="form">
      <div class="form-group">
        <label for="date">Sale Date</label>
        <input type="date" v-model="sale.date" id="date" required class="form-input" />
      </div>
      <div class="form-group">
        <label for="store">Store</label>
        <select v-model="store" id="store" @change="fetchProductsByStore" required class="form-select">
          <option v-for="store in stores" :key="store.id" :value="store">{{ store.name }}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="client">Client</label>
        <select v-model="sale.client" id="client" required class="form-select">
          <option v-for="client in clients" :key="client.id" :value="client">{{ client.name }}</option>
        </select>
      </div>
      <div v-for="(item, index) in sale.items" :key="index" class="form-group item-group">
        <div class="item-select">
          <label for="product">Product</label>
          <select v-model="item.inventoryItem" id="product" @change="updateTotalAmount" required class="form-select">
            <option v-for="inv in inventory" :key="inv.id" :value="inv">{{ inv.product.name }}</option>
          </select>
        </div>
        <div class="item-quantity">
          <label for="quantity">Quantity</label>
          <input type="number" v-model="item.quantity" id="quantity" required min="1" @input="updateTotalAmount" class="form-input" />
        </div>
        <button type="button" @click="removeItem(index)" class="remove-item-btn">Remove</button>
      </div>
      <button type="button" @click="addItem" class="add-item-btn">Add Item</button>
      <div class="total-amount">
        <label>Total Amount</label>
        <span class="amount">{{ totalAmount }}</span>
      </div>
      <button type="submit" :disabled="!isFormValid" class="submit-btn">Submit Sale</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      store: null,
      sale: {
        date: null,  // Added date property
        client: null,
        totalAmount: 0,
        items: [
          {
            inventoryItem: null,
            quantity: 1,
          },
        ],
      },
      stores: [],
      clients: [],
      inventory: [],
    };
  },
  methods: {
    async submitForm() {
      try {
        for (const item of this.sale.items) {
          if(item.inventoryItem.quantity < item.quantity) {
            alert('Not enough stock for ' + item.inventoryItem.product.name);
            return;
          }
        }
        const response = await axios.post('http://localhost:8081/api/sales/add', this.sale, {
          headers: {
            'Content-Type': 'application/json',
          },
        });
        alert('Sale added successfully!');
        for (const item of this.sale.items) {
          if(item.inventoryItem.quantity === item.quantity){
          alert("The product "+item.inventoryItem.product.name + ' is no longer availabe in '+this.store.name)
        }
        }

      } catch (error) {
        alert(error.message);
      }
    },

    addItem() {
      this.sale.items.push({
        inventoryItem: null,  // Correctly use `inventoryItem` instead of `product`
        quantity: 1,
      });
    },

    removeItem(index) {
      this.sale.items.splice(index, 1);
      this.updateTotalAmount();  // Recalculate total after removing item
    },

    async fetchProductsByStore() {
      if (this.store && this.store.id) {
        try {
          const response = await axios.get(`http://localhost:8081/api/inventoryItems/byStore/${this.store.id}`);
          this.inventory = response.data;
        } catch (error) {
          console.error('Error fetching products:', error);
        }
      }
    },

    updateTotalAmount() {
      this.sale.totalAmount = this.totalAmount; 
    },
  },
  computed: {
    isFormValid() {
      // Check if all required fields are filled and the sale items are valid
      return (
        this.sale.date &&
        this.store &&
        this.sale.client &&
        this.sale.items.length > 0 &&
        this.sale.items.every(item => item.inventoryItem && item.quantity > 0)
      );
    },

    totalAmount() {
      // Calculate total amount for the sale
      return this.sale.items.reduce((total, item) => {
        if (item.inventoryItem && item.quantity > 0) {
          return total + item.inventoryItem.product.price * item.quantity;  // Use `inventoryItem.product` correctly
        }
        return total;
      }, 0).toFixed(2);  // Ensuring the total amount has two decimal places
    },
  },
  async created() {
    try {
      const [storesResponse, clientsResponse] = await Promise.all([
        axios.get('http://localhost:8081/api/stores/all'),
        axios.get('http://localhost:8081/api/clients/all'),
      ]);
      console.log(storesResponse.data);
      
      this.stores = storesResponse.data;
      this.clients = clientsResponse.data;
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  },
};
</script>
<style scoped>
.sale-form-container {
  background-color: #ffffff;
  padding: 2rem;
  border-radius: 1rem;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  width: 100%;
  margin: 3rem auto;
  font-family: 'Inter', sans-serif;
}

.section-title {
  font-size: 1.75rem;
  font-weight: 700;
  color: #1f2937;
  margin-bottom: 1.5rem;
  text-align: center;
}

.form {
  display: flex;
  flex-direction: column;
  gap: 1.25rem;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.item-group {
  display: flex;
  gap: 1rem;
  flex-wrap: wrap;
  justify-content: space-between;
}

.item-select, .item-quantity {
  flex: 1;
}

label {
  font-size: 1rem;
  font-weight: 600;
  color: #374151;
  margin-bottom: 0.5rem;
}

.form-input,
.form-select {
  padding: 0.75rem;
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
  padding: 0.75rem 1.25rem;
  border: none;
  border-radius: 0.75rem;
  font-size: 1rem;
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
  padding: 0.5rem 1rem;
  font-size: 0.875rem;
  margin-top: 1rem;
  align-self: flex-start;
}

.add-item-btn {
  background-color: #22c55e;
  margin-top: 1rem;
}

.total-amount {
  font-size: 1.25rem;
  font-weight: 700;
  margin-top: 1.5rem;
  color: #333;
}

.amount {
  font-size: 1.5rem;
  color: #4caf50;
}

/* Responsive Design */
@media (max-width: 600px) {
  .sale-form-container {
    padding: 1.5rem;
  }

  .item-group {
    flex-direction: column;
  }

  button {
    width: 100%;
  }

  .remove-item-btn {
    width: 100%;
  }

  .item-select, .item-quantity {
    width: 100%;
  }
}

</style>
