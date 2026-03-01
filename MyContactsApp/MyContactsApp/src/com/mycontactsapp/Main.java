/*
 // - Use Case-9: Search Contacts
 // - User searches contacts by name, phone, email, or tags.
 //
 // - Implements search based on various parameters such as name, email, birthdate, etc.
 // 
 // - @author Developer
 // - @version 9.0
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
			return;
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
			System.out.println("0. Continue to Contact Menu");

			int choice = Integer.parseInt(sc.nextLine());

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter new name: ");
					profileService.updateFullName(session.getLoggedInUser(), sc.nextLine());
					break;

				case 2:
					System.out.print("Enter new email: ");
					profileService.updateEmail(session.getLoggedInUser(), sc.nextLine());
					break;

				case 3:
					System.out.print("Enter new password: ");
					profileService.changePassword(session.getLoggedInUser(), sc.nextLine());
					break;

				case 4:
					User u = session.getLoggedInUser();
					System.out.println("\n--- Profile ---");
					System.out.println("Name: " + u.getname());
					System.out.println("Email: " + u.getEmail());
					System.out.println("User Type: " + u.getUserType());
					break;

				case 0:
					profileMenuRunning = false;
					break;

				default:
					System.out.println("Invalid choice.");
				}

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}


		// ============================
		// CONTACT MANAGEMENT (UC4–UC9)
		// ============================

		boolean contactsMenuRunning = true;

		while (contactsMenuRunning) {
			System.out.println("\n=== Contacts Menu ===");
			System.out.println("1. Create New Contact");
			System.out.println("2. View Contact Details");
			System.out.println("3. Edit Contact");
			System.out.println("4. Delete Contact");     
			System.out.println("5. Bulk Operations");
			System.out.println("6. Search Contacts");
			System.out.println("0. Exit Contacts Menu");

			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) {

			// UC-04 Create Contact
			case 1: {
				System.out.println("\n=== Create Contact ===");
				System.out.println("1. Person Contact");
				System.out.println("2. Organization Contact");
				int contactType = Integer.parseInt(sc.nextLine());

				System.out.print("Enter name: ");
				String name = sc.nextLine();

				System.out.print("Enter email: ");
				String email = sc.nextLine();

				List<PhoneNumber> phones = new ArrayList<>();
				System.out.print("How many phone numbers? ");
				int pc = Integer.parseInt(sc.nextLine());

				for (int i = 0; i < pc; i++) {
					System.out.print("Label: ");
					String label = sc.nextLine();
					System.out.print("Number: ");
					String number = sc.nextLine();
					phones.add(new PhoneNumber(label, number));
				}

				if (contactType == 1) {
					System.out.print("Enter birthday: ");
					String birthday = sc.nextLine();
					PersonContact p = contactService.createPersonContact(name, email, birthday, phones);
					System.out.println("\nPerson Contact Created: " + p.getName());
				} else {
					System.out.print("Enter website: ");
					String website = sc.nextLine();
					OrganizationContact o = contactService.createOrganizationContact(name, email, website, phones);
					System.out.println("\nOrganization Contact Created: " + o.getName());
				}
			}
			break;

			// UC-05 View Contact
			case 2: {
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
			}
			break;

			// UC-06 Edit Contact
			case 3: {
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

					if (contact instanceof PersonContact) {
						System.out.println("5. Edit Birthday");
					} else {
						System.out.println("5. Edit Website");
					}

					System.out.println("0. Back");

					int choice2 = Integer.parseInt(sc.nextLine());

					try {
						switch (choice2) {
						case 1:
							System.out.print("Enter new name: ");
							contact.setName(sc.nextLine());
							break;

						case 2:
							System.out.print("Enter new email: ");
							contact.setEmail(sc.nextLine());
							break;

						case 3:
							System.out.print("Label: ");
							String label = sc.nextLine();
							System.out.print("Number: ");
							String num = sc.nextLine();
							contact.addPhoneNumber(new PhoneNumber(label, num));
							break;

						case 4:
							List<PhoneNumber> phs = contact.getPhoneNumbers();
							if (phs.isEmpty()) {
								System.out.println("No phone numbers.");
								break;
							}

							for (int i = 0; i < phs.size(); i++) {
								System.out.println((i + 1) + ". " +
										phs.get(i).getLabel() + ": " + phs.get(i).getNumber());
							}

							System.out.print("Remove which? ");
							int removeIdx = Integer.parseInt(sc.nextLine()) - 1;
							contact.removePhoneNumber(removeIdx);
							break;

						case 5:
							if (contact instanceof PersonContact pco) {
								System.out.print("Enter new birthday: ");
								pco.setBirthday(sc.nextLine());
							} else {
								OrganizationContact oc = (OrganizationContact) contact;
								System.out.print("Enter new website: ");
								oc.setWebsite(sc.nextLine());
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
			}
			break;

			// UC-07 DELETE CONTACT (Correct location)
			case 4: {
				List<Contact> all = contactStore.getAll();

				if (all.isEmpty()) {
					System.out.println("No contacts to delete.");
					break;
				}

				System.out.println("\n=== Select Contact to Delete ===");
				for (int i = 0; i < all.size(); i++) {
					System.out.println((i + 1) + ". " + all.get(i).getName());
				}

				System.out.print("Enter number: ");
				int delIndex = Integer.parseInt(sc.nextLine()) - 1;

				if (delIndex < 0 || delIndex >= all.size()) {
					System.out.println("Invalid selection.");
					break;
				}

				Contact toDelete = all.get(delIndex);

				System.out.println("\nAre you sure you want to delete: " + toDelete.getName() + "?");
				System.out.print("Type YES to confirm: ");
				String confirm = sc.nextLine();

				if (confirm.equalsIgnoreCase("YES")) {
					contactStore.delete(toDelete);
					System.out.println("Contact deleted.");
				} else {
					System.out.println("Cancelled.");
				}
			}
			break;
			// UC-08 BULK OPERATIONS
			case 5: {
				boolean bulkMenu = true;

				while (bulkMenu) {
					System.out.println("\n=== Bulk Operations ===");
					System.out.println("1. Bulk Delete");
					System.out.println("2. Bulk Tag");
					System.out.println("3. Bulk Export");
					System.out.println("0. Back");

					int op = Integer.parseInt(sc.nextLine());

					switch (op) {
					case 1:
						BulkOperations.bulkDelete(sc, contactStore);
						break;

					case 2:
						BulkOperations.bulkTag(sc, contactStore);
						break;

					case 3:
						BulkOperations.bulkExport(sc, contactStore);
						break;

					case 0:
						bulkMenu = false;
						break;

					default:
						System.out.println("Invalid option.");
					}
				}
			}
			break;
			// UC-09 SEARCH CONTACTS
			case 6: {
			    SearchService searchService = new SearchService();

			    System.out.println("\n=== Search Contacts ===");
			    System.out.println("1. Search by Name");
			    System.out.println("2. Search by Email");
			    System.out.println("3. Search by Phone");
			    System.out.println("4. Search by Tag");
			    System.out.println("0. Back");

			    int searchChoice = Integer.parseInt(sc.nextLine());

			    List<Contact> results = new ArrayList<>();

			    switch (searchChoice) {
			        case 1:
			            System.out.print("Enter name keyword: ");
			            results = searchService.searchByName(sc.nextLine(), contactStore);
			            break;

			        case 2:
			            System.out.print("Enter email: ");
			            results = searchService.searchByEmail(sc.nextLine(), contactStore);
			            break;

			        case 3:
			            System.out.print("Enter phone number: ");
			            results = searchService.searchByPhone(sc.nextLine(), contactStore);
			            break;

			        case 4:
			            System.out.print("Enter tag: ");
			            results = searchService.searchByTag(sc.nextLine(), contactStore);
			            break;

			        case 0:
			            break;

			        default:
			            System.out.println("Invalid option.");
			    }

			    if (!results.isEmpty()) {
			        System.out.println("\n=== Search Results ===");
			        for (Contact c : results) {
			            System.out.println("- " + c.getName() + " (" + c.getId() + ")");
			        }
			    } else {
			        System.out.println("No matching contacts found.");
			    }
			}
			break;
			case 0:
				contactsMenuRunning = false;
				break;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}
}