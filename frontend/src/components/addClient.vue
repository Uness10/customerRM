<template>
  <div class="add-client-form">
    <h1 class="section-title">{{ isEditing ? "Edit Client" : "Add New Client" }}</h1>

    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" v-model="client.name" id="name" placeholder="Client Name" required />
      </div>

      <div class="form-group">
        <label for="contactInfo">Contact Info</label>
        <input type="text" v-model="client.contactInfo" id="contactInfo" placeholder="Client Contact Info" required />
      </div>

      <button type="submit" class="submit-btn">{{ isEditing ? "Update Client" : "Add Client" }}</button>
      <button type="button" class="cancel-btn" @click="closeForm">Cancel</button>
    </form>
  </div>
</template>

<script>
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
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  font-size: 1rem;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.submit-btn {
  padding: 12px 20px;
  background-color: #28a745;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  border: none;
  border-radius: 5px;
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
  margin-left: 10px;
}

.cancel-btn:hover {
  background-color: #999;
}
</style>
