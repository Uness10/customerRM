package com.crm.customerRM.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crm.customerRM.entities.Admin;
import com.crm.customerRM.models.AdminModel;

@Controller
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminModel adminmodel;

    // Display login form
    @RequestMapping("/login")
    public String showLoginForm() {
        return "login";  // This will load login.html from the templates folder
    }

    // Handle the login form submission
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            Admin admin = adminmodel.login(username, password);
            model.addAttribute("admin", admin);
            return "redirect:/dashboard";  // Redirect to the dashboard after successful login
        } catch (Exception e) {
            model.addAttribute("error", true);  // Add an error attribute to show the error message
            return "login";  // Return to the login page with the error message
        }
    }

    // Display dashboard page
    @RequestMapping("/dashboard")
    public String showDashboard(Model model) {
        // This method is invoked when the user successfully logs in.
        // The admin object is already added to the model in the login method.
        return "dashboard";  // Load the dashboard.html template
    }

    // Add logout functionality (optional)
    @RequestMapping("/logout")
    public String logout() {
        // Perform logout actions here if needed (e.g., clearing session)
        return "redirect:/api/admins/login";  // Redirect back to the login page after logout
    }
}
