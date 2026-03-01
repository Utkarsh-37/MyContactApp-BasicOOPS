package com.mycontactsapp.contactmanagement;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BulkOperations {

    public static void bulkDelete(Scanner sc, ContactStore store) {

        List<Contact> all = store.getAll();

        if (all.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n=== Select Contacts to DELETE ===");
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i).getName());
        }

        System.out.print("Enter numbers separated by commas: ");
        String[] parts = sc.nextLine().split(",");

        List<Contact> toDelete = new ArrayList<>();

        for (String p : parts) {
            int idx = Integer.parseInt(p.trim()) - 1;
            if (idx >= 0 && idx < all.size()) {
                toDelete.add(all.get(idx));
            }
        }

        System.out.print("Type YES to confirm deletion: ");
        if (!sc.nextLine().equalsIgnoreCase("YES")) {
            System.out.println("Cancelled.");
            return;
        }

        for (Contact c : toDelete) {
            store.delete(c);
        }

        System.out.println("Bulk deletion completed.");
    }


    public static void bulkTag(Scanner sc, ContactStore store) {

        List<Contact> all = store.getAll();
        if (all.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n=== Select Contacts to TAG ===");
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i).getName());
        }

        System.out.print("Enter numbers: ");
        String[] parts = sc.nextLine().split(",");

        List<Contact> selected = new ArrayList<>();
        for (String p : parts) {
            int idx = Integer.parseInt(p.trim()) - 1;
            if (idx >= 0 && idx < all.size()) {
                selected.add(all.get(idx));
            }
        }

        System.out.print("Enter tag: ");
        String tag = sc.nextLine();

        for (Contact c : selected) {
            c.addTag(tag);
        }

        System.out.println("Tag applied to selected contacts.");
    }


    public static void bulkExport(Scanner sc, ContactStore store) {

        List<Contact> all = store.getAll();
        if (all.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n=== Select Contacts to EXPORT ===");
        for (int i = 0; i < all.size(); i++) {
            System.out.println((i + 1) + ". " + all.get(i).getName());
        }

        System.out.print("Enter numbers: ");
        String[] parts = sc.nextLine().split(",");

        System.out.println("\n=== EXPORT OUTPUT ===");

        for (String p : parts) {
            int idx = Integer.parseInt(p.trim()) - 1;
            if (idx >= 0 && idx < all.size()) {
                System.out.println(all.get(idx).toString());
            }
        }

        System.out.println("Export completed.");
    }
}
