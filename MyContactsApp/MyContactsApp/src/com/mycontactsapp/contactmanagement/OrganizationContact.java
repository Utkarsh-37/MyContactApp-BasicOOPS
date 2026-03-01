package com.mycontactsapp.contactmanagement;

public class OrganizationContact extends Contact {

    private String website;

    public OrganizationContact(String name, String email, String website) {
        super(name, email);
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    
    @Override
    public String toString() {
        return super.toString() +
               "Website: " + website + "\n";
    }
}