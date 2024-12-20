<template>
    <div class="add-store-form">
      <h1 class="section-title">{{ isEditing ? "Edit Store" : "Add New Store" }}</h1>
  
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="name">Store Name</label>
          <input
            type="text"
            v-model="store.name"
            id="name"
            placeholder="Enter Store Name"
            required
          />
        </div>
  
        <div class="form-group">
          <label for="location">Location</label>
          <input
            type="text"
            v-model="store.location"
            id="location"
            placeholder="Enter Store Location"
            required
          />
        </div>
  
        <button type="submit" class="submit-btn">
          {{ isEditing ? "Update Store" : "Add Store" }}
        </button>
        <button type="button" class="cancel-btn" @click="closeForm">
          Cancel
        </button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        store: {
          name: "",
          location: "",
          inventory: [],
        },
        isEditing: false,
      };
    },
    props: {
      storeId: {
        type: Number,
        required: false,
      },
    },
    methods: {
      submitForm() {
        if (this.isEditing) {
          axios
            .put("http://localhost:8081/api/stores/update", this.store)
            .then(() => {
              this.$emit("close"); // Close the form
              location.reload();
            });
        } else {
          axios
            .post("http://localhost:8081/api/stores/add", this.store)
            .then(() => {
              this.$emit("close"); // Close the form
              location.reload();
            });
        }
      },
      closeForm() {
        this.$emit("close"); // Close the form when the user clicks cancel
      },
    },
    created() {
      if (this.storeId) {
        this.isEditing = true;
        axios
          .get(`http://localhost:8081/api/stores/${this.storeId}`)
          .then((response) => {
            this.store = response.data;
          });
      }
    },
  };
  </script>
  
  <style scoped>
  .add-store-form {
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