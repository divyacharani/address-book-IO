package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.time.LocalDate;
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

	// To test retrieved entries from database
	@Test
	public void givenAddressBookDatabaseWhenRetrievedShouldMatchContactsCount() {
		try {
			contactList = addressBookService.readData();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(5, contactList.size());
	}

	// To test whether database is updated for a given entry or not
	@Test
	public void givenUpdatedPhoneNumberWhenUpdatedShouldSyncWithDatabase() {
		boolean result = false;
		try {
			addressBookService.updateData("Rachel", "Green", 9875961560L);
			result = addressBookService.checkContactsInsyncWithDatabase("Rachel", "Green");
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertTrue(result);
	}

	// To test the retrieved entries for a given data range
	@Test
	public void givenDateRangeWhenRetrievedSouldMatchContactsCount() {
		try {
			contactList = addressBookService.getContactsByDate(LocalDate.of(2020, 05, 01), LocalDate.now());
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertEquals(3, contactList.size());
	}

	// To test the retrieved entries for a given city or state
	@Test
	public void givenCityStateWhenRetrievedShouldMatchContactsCount() {
		try {
			contactList = addressBookService.getContactsByCityOrState("Mumbai", "Maharashtra");
		} catch (DatabaseException e) {
			e.printStackTrace();
		} 
		assertEquals(2, contactList.size());
	}

}
