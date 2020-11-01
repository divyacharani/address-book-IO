package com.bridgelabz.AddressBookIO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookService {

	public static final Logger LOG = LogManager.getLogger(AddressBookService.class);
	private AddressBookDBService addressBookDBService;
	private List<Contact> contactList = new ArrayList<Contact>();

	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public static void main(String[] args) {
		// Welcome Message
		LOG.info("Welcome to Address Book Program ");
	}

	// To read data from database
	public List<Contact> readData() throws DatabaseException {
		contactList = addressBookDBService.readDataDB();
		return contactList;
	}

	// To update database
	public void updateData(String firstName, String lastName, long phoneNumber) throws DatabaseException {
		contactList = addressBookDBService.readDataDB();
		int rowAffected = addressBookDBService.updateDataDB(firstName, lastName, phoneNumber);
		if (rowAffected != 0)
			(getContactByName(contactList, firstName, lastName)).setPhoneNumber(phoneNumber);
	}

	private Contact getContactByName(List<Contact> contactList, String firstName, String lastName)
			throws DatabaseException {
		Contact contact = contactList.stream().filter(contactObj -> (((contactObj.getFirstName()).equals(firstName))
				&& ((contactObj.getLastName()).equals(lastName)))).findAny().orElse(null);
		return contact;

	}

	public boolean checkContactsInsyncWithDatabase(String firstName, String lastName) throws DatabaseException {
		boolean result = false;
		contactList = addressBookDBService.readDataDB();
		Contact contactFromDb = addressBookDBService.getContactByNameFromDB(firstName, lastName).get(0);
		result = getContactByName(contactList, firstName, lastName).equals(contactFromDb);
		return result;
	}
}
