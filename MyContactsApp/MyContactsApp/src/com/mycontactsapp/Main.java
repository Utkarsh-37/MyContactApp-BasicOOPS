/*
 // - Use Case-6: Edit Contact
 // - User modifies existing contact information.
 //
 // - Logged-in user selects a contact (by UUID or index) and updates name, phone, or email.
 // - Validation is applied via setters; changes are saved by replacing the original with the edited copy.
 // 
 // - @author Developer
 // - @version 6.0
*/
package com.mycontactsapp;

import com.mycontactsapp.usermanagement.*;
import com.mycontactsapp.contactmanagement.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		UserStore userStore = new UserStore();
		RegistrationService regService = new RegistrationService();
		AuthService authService = new AuthService(userStore);
		SessionManager session = new SessionManager();
		ContactStore contactStore = new ContactStore();
		ContactService contactService = new ContactService(contactStore);

		// ============================
		// 1. USER REGISTRATION
		// ============================
		System.out.println("=== User Registration ===");

		try {
			System.out.print("Full Name: ");
			String fullName = sc.nextLine();

			System.out.print("Email: ");
			String email = sc.nextLine();

			System.out.print("Password: ");
			String password = sc.nextLine();

			System.out.print("User Type (FREE / PREMIUM): ");
			String type = sc.nextLine();

			User user = regService.register(email, password, fullName, type);

			userStore.saveUser(user);
			System.out.println("\nRegistration Successful!");

		} catch (Exception e) {
			System.out.println("Registration failed: " + e.getMessage());
		}


		// ============================
		// 2. USER LOGIN
		// ============================
		System.out.println("\n=== User Login ===");
		try {
			System.out.print("Email: ");
			String email = sc.nextLine();

			System.out.print("Password: ");
			String password = sc.nextLine();

			User loggedUser = authService.login(email, password);

			session.login(loggedUser);

			System.out.println("\nLogin Successful!");
			System.out.println("Welcome, " + session.getLoggedInUser().getname());
			System.out.println("User Type: " + session.getLoggedInUser().getUserType());

		} catch (Exception e) {
			System.out.println("Login failed: " + e.getMessage());
			return; // Stop program if login fails
		}


		// ============================
		// 3. PROFILE MANAGEMENT MENU
		// ============================
		System.out.println("\n=== Profile Management ===");

		ProfileService profileService = new ProfileService();

		boolean profileMenuRunning = true;

		while (profileMenuRunning) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Update Name");
			System.out.println("2. Update Email");
			System.out.println("3. Change Password");
			System.out.println("4. View Profile");
			System.out.println("0. Continue to Contact Creation");

			int choice = Integer.parseInt(sc.nextLine());

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter new name: ");
					profileService.updateFullName(session.getLoggedInUser(), sc.nextLine());
					System.out.println("Name updated.");
					break;

				case 2:
					System.out.print("Enter new email: ");
					profileService.updateEmail(session.getLoggedInUser(), sc.nextLine());
					System.out.println("Email updated.");
					break;

				case 3:
					System.out.print("Enter new password: ");
					profileService.changePassword(session.getLoggedInUser(), sc.nextLine());
					System.out.println("Password changed.");
					break;

				case 4:
					User u = session.getLoggedInUser();
					System.out.println("\n--- Profile ---");
					System.out.println("Name: " + u.getname());
					System.out.println("Email: " + u.getEmail());
					System.out.println("User Type: " + u.getUserType());
					break;

				case 0:
					profileMenuRunning = false; // exit loop
					break;

				default:
					System.out.println("Invalid choice.");
				}

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		// ============================
		// Contact Management (UC4, UC5)
		// ============================

		boolean contactsMenuRunning = true;

		while (contactsMenuRunning) {
			System.out.println("\n=== Contacts Menu ===");
			System.out.println("1. Create New Contact");
			System.out.println("2. View Contact Details");
			System.out.println("3. Edit Contact");
			System.out.println("0. Exit Contacts Menu");

			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
			case 1:
				System.out.println("\n=== Create Contact ===");
				System.out.println("Choose type:");
				System.out.println("1. Person Contact");
				System.out.println("2. Organization Contact");

				int contactType = Integer.parseInt(sc.nextLine());

				System.out.print("Enter name: ");
				String name = sc.nextLine();

				System.out.print("Enter email: ");
				String email = sc.nextLine();

				// ---- phone numbers ----
				List<PhoneNumber> phones = new ArrayList<>();
				System.out.print("How many phone numbers? ");
				int pc = Integer.parseInt(sc.nextLine());

				for (int i = 0; i < pc; i++) {
					System.out.print("Label (Home/Mobile/Work): ");
					String label = sc.nextLine();
					System.out.print("Number: ");
					String number = sc.nextLine();
					phones.add(new PhoneNumber(label, number));
				}

				if (contactType == 1) {
					System.out.print("Enter birthday: ");
					String birthday = sc.nextLine();

					PersonContact p = contactService.createPersonContact(name, email, birthday, phones);
					System.out.println("\nPerson Contact Created: " + p.getName() + " (" + p.getId() + ")");
				}
				else {
					System.out.print("Enter website: ");
					String website = sc.nextLine();

					OrganizationContact o = contactService.createOrganizationContact(name, email, website, phones);
					System.out.println("\nOrganization Contact Created: " + o.getName() + " (" + o.getId() + ")");
				}
				break;
			case 2:
				List<Contact> all = contactStore.getAll();

				if (all.isEmpty()) {
					System.out.println("No contacts available.");
					break;
				}

				System.out.println("\n=== Contact List ===");
				for (int i = 0; i < all.size(); i++) {
					System.out.println((i + 1) + ". " + all.get(i).getName());
				}

				System.out.print("Choose contact #: ");
				int index = Integer.parseInt(sc.nextLine()) - 1;

				if (index < 0 || index >= all.size()) {
					System.out.println("Invalid selection.");
					break;
				}

				Contact selected = all.get(index);

				System.out.println("\n=== Contact Details ===");
				System.out.println(selected.toString());
				break;
			case 3:
				List<Contact> allContacts = contactStore.getAll();

				if (allContacts.isEmpty()) {
					System.out.println("No contacts to edit.");
					break;
				}

				System.out.println("\n=== Select Contact to Edit ===");
				for (int i = 0; i < allContacts.size(); i++) {
					System.out.println((i + 1) + ". " + allContacts.get(i).getName());
				}

				System.out.print("Enter number: ");
				int idx = Integer.parseInt(sc.nextLine()) - 1;

				if (idx < 0 || idx >= allContacts.size()) {
					System.out.println("Invalid selection.");
					break;
				}

				Contact contact = allContacts.get(idx);
				boolean editing = true;

				while (editing) {
					System.out.println("\n=== Edit Menu ===");
					System.out.println("1. Edit Name");
					System.out.println("2. Edit Email");
					System.out.println("3. Add Phone Number");
					System.out.println("4. Remove Phone Number");

					// Person vs Organization options
					if (contact instanceof PersonContact) {
						System.out.println("5. Edit Birthday");
					} else {
						System.out.println("5. Edit Website");
					}

					System.out.println("0. Done Editing");

					int choice2 = Integer.parseInt(sc.nextLine());

					try {
						switch (choice2) {
						case 1:
							System.out.print("Enter new name: ");
							contact.setName(sc.nextLine());
							System.out.println("Name updated.");
							break;

						case 2:
							System.out.print("Enter new email: ");
							contact.setEmail(sc.nextLine());
							System.out.println("Email updated.");
							break;

						case 3:
							System.out.print("Label: ");
							String label = sc.nextLine();
							System.out.print("Number: ");
							String num = sc.nextLine();
							contact.addPhoneNumber(new PhoneNumber(label, num));
							System.out.println("Phone added.");
							break;

						case 4:
							List<PhoneNumber> phs = contact.getPhoneNumbers();
							if (phs.isEmpty()) {
								System.out.println("No phone numbers to remove.");
								break;
							}
							for (int i = 0; i < phs.size(); i++) {
								System.out.println((i + 1) + ". " +
										phs.get(i).getLabel() + ":" + phs.get(i).getNumber());
							}
							System.out.print("Remove which? ");
							int removeIdx = Integer.parseInt(sc.nextLine()) - 1;
							contact.removePhoneNumber(removeIdx);
							System.out.println("Phone removed.");
							break;

						case 5:
							if (contact instanceof PersonContact pco) {
								System.out.print("Enter new birthday: ");
								pco.setBirthday(sc.nextLine());
								System.out.println("Birthday updated.");
							} else {
								OrganizationContact oc = (OrganizationContact) contact;
								System.out.print("Enter new website: ");
								oc.setWebsite(sc.nextLine());
								System.out.println("Website updated.");
							}
							break;

						case 0:
							editing = false;
							break;

						default:
							System.out.println("Invalid option.");
						}

					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
				}

				break;
			case 0:
				contactsMenuRunning = false;
				continue; // exit loop
			default:
				System.out.println("Invalid choice.");
			}
		}


	}
}
