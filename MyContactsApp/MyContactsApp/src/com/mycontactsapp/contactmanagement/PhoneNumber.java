package com.mycontactsapp.contactmanagement;

public class PhoneNumber {

    private String label;   // e.g. Home, Work, Mobile
    private String number;

    public PhoneNumber(String label, String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        this.label = label;
        this.number = number;
    }

    public String getLabel() { return label; }
    public String getNumber() { return number; }
}
