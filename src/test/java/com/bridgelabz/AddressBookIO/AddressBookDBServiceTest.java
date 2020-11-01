package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AddressBookDBServiceTest {
	private AddressBookService addressBookService;
	private List<Contact> contactList;

	@Before
	public void init() {
		addressBookService = new AddressBookService();
	}

	@Test
	public void givenAddressBookDatabaseWhenRetrievedShouldContactsCount() {
		try {
			contactList = addressBookService.readData();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(5, contactList.size());
	}
}
