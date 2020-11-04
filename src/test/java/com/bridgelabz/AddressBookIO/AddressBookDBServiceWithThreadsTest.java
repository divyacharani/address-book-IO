package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class AddressBookDBServiceWithThreadsTest {
	private AddressBookService addressBookService;
	private List<Contact> contactListDatabase;

	@Before
	public void init() {
		addressBookService = new AddressBookService();
	}

	
	@Test
	public void givenListOfContactssWhenAddedShouldMatchNoOfEntries() {
		List<Contact> contactList = new ArrayList<>();
		contactList.add(new Contact("Chandler", "Bing", "Begumpet", "Hyderabad", "Telangana", 533893, 9789784561L,"chandler@gmail.com", LocalDate.now(), 101, 51));
		contactList.add(new Contact("Rachel", "Green", "Thane", "Hyderabad", "Maharashtra", 676945, 8912345674L,"rachel@gmail.com", LocalDate.now(), 101, 50));
		contactList.add(new Contact("Monica", "Geller", "S.R.Nagar", "Chennai", "TamilNadu", 478945, 8796543298L,"monica@gmail.com", LocalDate.now(), 101, 52));
		contactList.add(new Contact("Joey", "Tribbiyani", "M.G.Road", "Bangalore", "karnataka", 967045, 9876543210L,"joey@gmail.com", LocalDate.now(), 101, 52));
		contactList.add(new Contact("Terisa", "Mark", "T.Nagar", "Hyderabad", "Telangana", 567945, 8905434567L,"terisa@gmail.com", LocalDate.now(), 102, 50));
		Instant start = null;
		Instant end = null;
		Instant startThread = null;
		Instant endThread = null;
		try {
			start = Instant.now();
			addressBookService.addEmployeeListToEmployeeAndPayrollTable(contactList);
			end = Instant.now();
			startThread = Instant.now();
			addressBookService.addEmployeeListToEmployeeAndPayrollWithThreads(contactList);
			endThread = Instant.now();
			contactListDatabase = addressBookService.readData();
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		AddressBookService.LOG.info("Duration without Threads : " + Duration.between(start, end));
		AddressBookService.LOG.info("Duration with Threads : " + Duration.between(startThread, endThread));
		
		assertEquals(10, contactListDatabase.size());
	}

}
