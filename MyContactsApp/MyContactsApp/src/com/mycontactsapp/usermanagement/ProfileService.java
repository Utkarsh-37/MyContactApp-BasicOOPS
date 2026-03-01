package com.mycontactsapp.usermanagement;

public class ProfileService {

    public void updateFullName(User user, String newName) {
        user.setname(newName);
    }

    public void updateEmail(User user, String newEmail) {
        user.setEmail(newEmail);
    }

    public void changePassword(User user, String newPassword) throws Exception {
        user.setPassword(newPassword);
    }

}