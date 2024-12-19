<template>
  <div class="add-client-form">
    <h1 class="section-title">{{ isEditing ? "Edit Client" : "Add New Client" }}</h1>

    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" v-model="client.name" id="name" placeholder="Enter Client Name" required />
      </div>

      <div class="form-group">
        <label for="contactInfo">Contact Info</label>
        <input type="text" v-model="client.contactInfo" id="contactInfo" placeholder="Enter Client Contact Info" required />
      </div>

      <div class="form-actions">
        <button type="submit" class="submit-btn">{{ isEditing ? "Update Client" : "Add Client" }}</button>
        <button type="button" class="cancel-btn" @click="closeForm">Cancel</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      client: {
        name: '',
        contactInfo: '',
      },
      isEditing: false,
    };
  },
  props: {
    clientId: {
      type: Number,
      required: false
    }
  },
  methods: {
    submitForm() {
      if (this.isEditing) {
        axios.put('http://localhost:8081/api/clients/update', this.client)
          .then(() => {
            this.$emit('close'); // Close the form
            this.$router.push('/clients'); // Redirect to the clients list
          });
      } else {
        axios.post('http://localhost:8081/api/clients/add', this.client)
          .then(() => {
            this.$emit('close'); // Close the form
            this.$router.push('/clients'); // Redirect to the clients list
          });
      }
    },
    closeForm() {
      this.$emit('close'); // Close the form when user clicks cancel
    }
  },
  created() {
    if (this.clientId) {
      this.isEditing = true;
      axios.get(`http://localhost:8081/api/clients/${this.clientId}`)
        .then(response => {
          this.client = response.data;
        });
    }
  }
};
</script>

<style scoped>
.add-client-form {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  margin: 0 auto;
  text-align: center; /* Centering content */
}

.form-group {
  margin-bottom: 16px; /* Reduced space between form fields */
}

label {
  display: block;
  font-size: 1rem;
  margin-bottom: 5px;
  color: #333;
}

input {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 1rem;
  box-sizing: border-box; /* Ensures padding doesn't affect the width */
  margin-top: 5px;
}

input:focus {
  border-color: #007bff; /* Highlight the input on focus */
  outline: none;
}

.form-actions {
  display: flex;
  justify-content: center; /* Center buttons horizontally */
  gap: 10px; /* Space between buttons */
  margin-top: 20px;
}

.submit-btn {
  padding: 12px 20px;
  background-color: #28a745;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  width: 150px; /* Fixed width for buttons */
}

.submit-btn:hover {
  background-color: #218838;
}

.cancel-btn {
  padding: 12px 20px;
  background-color: #ccc;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  width: 150px;
}

.cancel-btn:hover {
  background-color: #999;
}

/* Responsive design */
@media (max-width: 600px) {
  .add-client-form {
    padding: 15px;
  }

  .submit-btn, .cancel-btn {
    width: 100%; /* Buttons take full width on small screens */
  }
}

</style>
