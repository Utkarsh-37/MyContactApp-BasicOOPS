package com.mycontactsapp.usermanagement;

public class AuthService 
{

    private UserStore userStore;

    public AuthService(UserStore userStore) 
    {
        this.userStore = userStore;
    }

    public User login(String email, String password) throws Exception 
    {

        User found = userStore.findByEmail(email);

        if (found == null) 
        {
            throw new IllegalArgumentException("No user found with that email.");
        }

        String hashed = PasswordHasher.hash(password);

        if (!hashed.equals(found.getPasswordHash())) 
        {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return found;
    }
}