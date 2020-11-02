package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

	// To test the retrieved entries count for a given city or state
	@Test
	public void givenCityStateWhenRetrievedShouldMatchContactsCount() {
		Map<String,Integer> stateToCount = null;
		try {
			stateToCount= addressBookService.getContactsCountByState();
		} catch (DatabaseException e) {
			e.printStackTrace();
		} 
		Integer count = 2;
		assertEquals(count, stateToCount.get("Maharashtra"));
	}
	
	// To test whether a new record when added impacted all the tables or not
	@Test
	public void givenNewRecordWhenAddedShouldImpactAllTables() {
		boolean result = false;
		Contact contact = new Contact("Terisa", "Mark", "T.Nagar", "Hyderabad", "Telangana", 567945, 8905434567L,"terisa@gmail.com", LocalDate.now(), 102, 52);
		try {
			addressBookService.addNewContact(contact);
			result = addressBookService.checkContactsInsyncWithDatabase("Terisa", "Mark");
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		assertTrue(result);
	}
}
