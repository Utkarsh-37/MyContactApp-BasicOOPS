/*
// - Use Case-1: User Registartion
// - User creates an account with email, password, and profile information
// - Input is validated
// - If it is a success user details are displayed
// - @author Developer
// - @version 1.0
*/
package com.mycontactsapp;
import com.mycontactsapp.usermanagement.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        try {
            System.out.print("Enter full name: ");
            String fullName = sc.nextLine();

            System.out.print("Enter email: ");
            String email = sc.nextLine();

            System.out.print("Enter password: ");
            String password = sc.nextLine();

            System.out.print("Enter user type (FREE / PREMIUM): ");
            String type = sc.nextLine();

            User user = service.register(email, password, fullName, type);

            System.out.println("\nRegistration Successful!");
            System.out.println("Welcome, " + user.getname());
            System.out.println("User Type: " + user.getUserType());
            System.out.println("Email: " + user.getEmail());

        } catch (Exception e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
