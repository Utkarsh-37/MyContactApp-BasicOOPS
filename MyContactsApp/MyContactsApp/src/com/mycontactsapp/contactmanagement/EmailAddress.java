package com.mycontactsapp.contactmanagement;
import com.mycontactsapp.usermanagement.*;

public class EmailAddress {

    private String label;   // e.g. Personal, Work
    private String email;

    public EmailAddress(String label, String email) {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.label = label;
        this.email = email;
    }

    public String getLabel() { return label; }
    public String getEmail() { return email; }
}
