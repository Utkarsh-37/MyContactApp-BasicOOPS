/*
// - Use Case-3: User Profile Management
// - User updates profile information,changes password,or manages preferences
// - User can update city, phone number, password
// - @author Developer
// - @version 3.0
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
        
        System.out.println("\n=== Profile Management ===");

        ProfileService profileService = new ProfileService();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Update Name");
            System.out.println("2. Update Email");
            System.out.println("3. Change Password");
            System.out.println("4. View Profile");
            System.out.println("0. Exit");

            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        profileService.updateFullName(session.getLoggedInUser(), sc.nextLine());
                        System.out.println("Name updated.");
                        break;

                    case 2:
                        System.out.print("Enter new email: ");
                        profileService.updateEmail(session.getLoggedInUser(), sc.nextLine());
                        System.out.println("Email updated.");
                        break;

                    case 3:
                        System.out.print("Enter new password: ");
                        profileService.changePassword(session.getLoggedInUser(), sc.nextLine());
                        System.out.println("Password changed.");
                        break;

                    case 4:
                        User u = session.getLoggedInUser();
                        System.out.println("\n--- Profile ---");
                        System.out.println("Name: " + u.getname());
                        System.out.println("Email: " + u.getEmail());
                        System.out.println("User Type: " + u.getUserType());
                        break;

                    case 0:
                        System.out.println("Exiting profile menu.");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
