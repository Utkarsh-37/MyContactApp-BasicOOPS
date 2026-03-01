package com.mycontactsapp.usermanagement;

public class RegistrationService {

    public User register(String email, String password, String name, String type) throws Exception {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty.");
        }

        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email format!");
        }

        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }

        String hashed = PasswordHasher.hash(password);

        // JUST SIMPLE IF-ELSE, NO FACTORY
        if (type.equalsIgnoreCase("FREE")) {
            return new FreeUser(email, hashed, name);
        } else if (type.equalsIgnoreCase("PREMIUM")) {
            return new PremiumUser(email, hashed, name);
        } else {
            throw new IllegalArgumentException("Unknown user type. Choose FREE or PREMIUM.");
        }
    }
}