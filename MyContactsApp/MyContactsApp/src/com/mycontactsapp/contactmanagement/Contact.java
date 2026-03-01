package com.mycontactsapp.contactmanagement;
import com.mycontactsapp.usermanagement.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Contact {

    private String id;
    private String name;
    private String email; // single email
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();
    private LocalDateTime createdAt;

    public Contact(String name, String email) {

        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");

        if (!EmailValidator.isValid(email))
            throw new IllegalArgumentException("Invalid email.");

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<PhoneNumber> getPhoneNumbers() { return phoneNumbers; }

    public void addPhoneNumber(PhoneNumber p) {
        phoneNumbers.add(p);
    }
}
