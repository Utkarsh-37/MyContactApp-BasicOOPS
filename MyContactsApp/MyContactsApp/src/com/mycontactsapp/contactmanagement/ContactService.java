package com.mycontactsapp.contactmanagement;
import java.util.*;

public class ContactService {

	private ContactStore store;

	public ContactService(ContactStore store) 
	{
		this.store = store;
	}

	public PersonContact createPersonContact(String name, String email, String birthday,
			List<PhoneNumber> phones) 
	{

		PersonContact p = new PersonContact(name, email, birthday);

		for (PhoneNumber ph : phones) p.addPhoneNumber(ph);

		store.save(p);
		return p;
	}

	public OrganizationContact createOrganizationContact(String name, String email, String website,
			List<PhoneNumber> phones) 
	{

		OrganizationContact o = new OrganizationContact(name, email, website);

		for (PhoneNumber ph : phones) o.addPhoneNumber(ph);

		store.save(o);
		return o;
	}
}