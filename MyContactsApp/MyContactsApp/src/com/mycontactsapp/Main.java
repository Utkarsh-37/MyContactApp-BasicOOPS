/*
// - Use Case-2: User Authentication
// - User logs in with credentials to access their contact list
// - Password is hashed using MessageDigest (SHA-256)
// - User can select to login using BasicAuth or OAuth
// - @author Developer
// - @version 2.0
*/
package com.mycontactsapp;
import com.mycontactsapp.usermanagement.*;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) 
    {

        Scanner sc = new Scanner(System.in);

        UserStore userStore = new UserStore();
        RegistrationService regService = new RegistrationService();
        AuthService authService = new AuthService(userStore);
        SessionManager session = new SessionManager();

        System.out.println("=== User Registration ===");

        try {
            System.out.print("Full Name: ");
            String fullName = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("User Type (FREE / PREMIUM): ");
            String type = sc.nextLine();

            User user = regService.register(email, password, fullName, type);

            userStore.saveUser(user);
            System.out.println("\nRegistration Successful!");

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }

        
        System.out.println("\n=== User Login ===");
        try {
            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            User loggedUser = authService.login(email, password);

            session.login(loggedUser);

            System.out.println("\nLogin Successful!");
            System.out.println("Welcome, " + session.getLoggedInUser().getname());
            System.out.println("User Type: " + session.getLoggedInUser().getUserType());

        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}
