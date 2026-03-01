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

    public void removePhoneNumber(int index) {
        if (index < 0 || index >= phoneNumbers.size()) {
            throw new IllegalArgumentException("Invalid phone index.");
        }
        phoneNumbers.remove(index);
    }

    public void addPhoneNumber(PhoneNumber phone) {
        phoneNumbers.add(phone);
    }
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email.");
        }
        this.email = email;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Contact ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Phone Numbers:\n");

        for (PhoneNumber p : phoneNumbers) {
            sb.append("  - ").append(p.getLabel())
              .append(": ").append(p.getNumber()).append("\n");
        }

        sb.append("Created At: ").append(createdAt).append("\n");

        return sb.toString();
    }
    
    
}
