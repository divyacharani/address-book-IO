package com.bridgelabz.AddressBookIO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookService {

	public static final Logger LOG = LogManager.getLogger(AddressBookService.class);
	private AddressBookDBService addressBookDBService;
	private List<Contact> contactList;

	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public AddressBookService(List<Contact> contactList) {
		this.contactList = new ArrayList<>(contactList);
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

	// To check the database after updating
	public boolean checkContactsInsyncWithDatabase(String firstName, String lastName) throws DatabaseException {
		boolean result = false;
		contactList = addressBookDBService.readDataDB();
		Contact contactFromDb = addressBookDBService.getContactByNameFromDB(firstName, lastName).get(0);
		result = getContactByName(contactList, firstName, lastName).equals(contactFromDb);
		return result;
	}

	// To get contacts created after a particular date
	public List<Contact> getContactsByDate(LocalDate startDate, LocalDate endDate) throws DatabaseException {
		return addressBookDBService.getContactsByDateDB(startDate, endDate);
	}

	// To get contacts by given city or state
	public Map<String, Integer> getContactsCountByState() throws DatabaseException {
		return addressBookDBService.getContactsCountByStateDB();
	}

	// To add new contact to database
	public void addNewContact(Contact contact) throws DatabaseException {
		Contact contactData = addressBookDBService.addNewContactDB(contact);
		if (contactData.getContactId() != -1)
			contactList.add(contactData);
	}

	public void addEmployeeListToEmployeeAndPayrollTable(List<Contact> contactList) throws DatabaseException {
		for(Contact contact : contactList)
		addNewContact(contact);
	}

	public void addEmployeeListToEmployeeAndPayrollWithThreads(List<Contact> contactList) {
		Map<Integer, Boolean> contactAditionStatus = new HashMap<>();
		contactList.forEach(contact -> {
			Runnable task = () -> {
				contactAditionStatus.put(contact.hashCode(), false);
				LOG.info("Contact being added : " + contact.getFirstName());
				try {
					addNewContact(contact);
				} catch (DatabaseException e) {
					e.printStackTrace();
				}
				contactAditionStatus.put(contact.hashCode(), true);
				LOG.info("Contact added : " + contact.getFirstName());
			};
			Thread thread = new Thread(task, contact.getFirstName());
			thread.start();
		});

		while (contactAditionStatus.containsValue(false)) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public long countEntries() {
		return contactList.size();
	}
}
