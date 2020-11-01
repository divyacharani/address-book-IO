package com.bridgelabz.AddressBookIO;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBookService {

	public static final Logger LOG = LogManager.getLogger(AddressBookService.class);
	private AddressBookDBService addressBookDBService;
	
	public AddressBookService() {
		addressBookDBService = AddressBookDBService.getInstance();
	}

	public static void main(String[] args) {

		// Welcome Message
		LOG.info("Welcome to Address Book Program ");
	}

	public List<Contact> readData() throws DatabaseException {
		return addressBookDBService.readDataDB();
	}


}
