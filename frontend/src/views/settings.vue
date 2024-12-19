<template>
    <div class="settings-section">
      <h1 class="section-title">Admin Settings</h1>
  

      <form @submit.prevent="updatePassword" class="settings-form">
        <div class="form-group">
          <label for="newPassword">New Password</label>
          <input
            type="password"
            v-model="newPassword"
            id="newPassword"
            placeholder="Enter your new password"
            required
            class="form-input"
          />
        </div>
  
        <div class="form-group">
          <label for="confirmNewPassword">Confirm New Password</label>
          <input
            type="password"
            v-model="confirmNewPassword"
            id="confirmNewPassword"
            placeholder="Confirm your new password"
            required
            class="form-input"
          />
        </div>
  
        <button type="submit" class="submit-btn" :disabled="!isPasswordFormValid">
          Update Password
        </button>
      </form>
  
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        admin: {
          username: "root",
          password: "",
        },
        newPassword: "",
        confirmNewPassword: "",
        errorMessage: "",
      };
    },
    computed: {
      isPasswordFormValid() {
        return this.newPassword && this.newPassword === this.confirmNewPassword;
      },
    },
    methods: {

      async updatePassword() {
        if (!this.isPasswordFormValid) {
          this.errorMessage = "Passwords do not match.";
          return;
        }
  
        const updateDetails = {
          username: this.admin.username,
          password: this.newPassword,
        };
  
        try {
          const response = await axios.put(
            "http://localhost:8081/api/admin/update",
            updateDetails,
            { headers: { "Content-Type": "application/json" } }
          );
          alert("Password updated successfully");
          this.errorMessage = "";
          this.newPassword = "";
          this.confirmNewPassword = "";
        } catch (error) {
          this.errorMessage =
            error.response?.data || "Failed to update password. Please try again later.";
        }
      },
    },

  };
  </script>
  
  <style scoped>
  /* Same styles as before */
  
  .settings-section {
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 600px;
    margin: 0 auto;
  }
  
  .section-title {
    font-size: 1.8rem;
    font-weight: bold;
    color: #1f2937;
    margin-bottom: 1.5rem;
    text-align: center;
  }
  
  .settings-form {
    display: flex;
    flex-direction: column;
    gap: 1rem;
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
  
  .form-input {
    padding: 10px;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid #ccc;
  }
  
  .form-input:focus {
    border-color: #3b82f6;
    box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.3);
    outline: none;
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
  
  .submit-btn:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
  
  .submit-btn:hover:not(:disabled) {
    background-color: #218838;
  }
  
  .error-message {
    color: #e53e3e;
    font-size: 0.9rem;
    margin-top: 1rem;
  }
  </style>
  