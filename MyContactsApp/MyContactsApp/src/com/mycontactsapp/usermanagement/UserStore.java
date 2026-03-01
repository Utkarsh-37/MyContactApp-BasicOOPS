package com.mycontactsapp.usermanagement;

import java.util.ArrayList;
import java.util.List;

public class UserStore 
{

    private List<User> users = new ArrayList<>();

    public void saveUser(User user) {
        users.add(user);
    }

    public User findByEmail(String email) 
    {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<User> getAllUsers() 
    {
        return users;
    }
}