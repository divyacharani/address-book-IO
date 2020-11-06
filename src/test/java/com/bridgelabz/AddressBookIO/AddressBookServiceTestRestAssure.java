package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressBookServiceTestRestAssure {

	@Before
	public void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 3000;
	}

	private Contact[] getContactList() {
		Response response = RestAssured.get("/contacts");
		AddressBookService.LOG.info("Contact entries in JSON Server :\n" + response.asString());
		Contact[] arrayOfContacts = new Gson().fromJson(response.asString(), Contact[].class);
		return arrayOfContacts;
	}

	@Test
	public void UC1givenAddressBookDataWhenRetrievedShouldMatchNoofEntries() {
		AddressBookService addressBookService;
		Contact[] arrayOfContacts = getContactList();
		addressBookService = new AddressBookService(Arrays.asList(arrayOfContacts));
		long entries = addressBookService.countEntries();
		assertEquals(3, entries);
	}

	private Response addContactToJsonServer(Contact contact) {
		String contactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJson);
		return request.post("/contacts");
	}

	@Test
	public void UC2givenContactListWhenAddedShouldMatchResponseCode() {
		AddressBookService addressBookService;
		Contact[] arrayOfContacts = getContactList();
		addressBookService = new AddressBookService(Arrays.asList(arrayOfContacts));
		List<Contact> contactList = new ArrayList<>();
		contactList.add(new Contact(0, "Terisa", "Mark", "Kukatpally", "Hyderabad", "Telangana", 567894, 8976111277L,
				"joey@gmail.com"));
		contactList.add(new Contact(0, "Phoebe", "Buffay", "K.T.Colony", "Bangalore", "Karnataka", 897654, 9955553277L,
				"phoebe@gmail.com"));
		contactList.add(new Contact(0, "Monica", "Geller", "Thane", "Mumbai", "Maharashtra", 345123, 8985312356L,
				"monica@gmail.com"));
		for (Contact contact : contactList) {
			Response response = addContactToJsonServer(contact);
			int statusCode = response.getStatusCode();
			assertEquals(201, statusCode);
			contact = new Gson().fromJson(response.asString(), Contact.class);
			addressBookService.addContact(contact);
		}
		long entries = addressBookService.countEntries();
		assertEquals(6, entries);
	}

	@Test
	public void UC3givenUpdatedPhoneNumberWhenUpdatedShouldMatchResponseCode() {
		AddressBookService addressBookService;
		Contact[] arrayOfContacts = getContactList();
		addressBookService = new AddressBookService(Arrays.asList(arrayOfContacts));
		addressBookService.updateContact("Phoebe", "Buffay", 9966663277L);
		Contact contact = addressBookService.getContact("Phoebe", "Buffay");
		String contactJson = new Gson().toJson(contact);
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(contactJson);
		Response response = request.put("/contacts/" + contact.getContactId());
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
	}

	@Test
	public void UC4givenContactWhenDeletedShouldMatchResponseCode() {
		AddressBookService addressBookService;
		Contact[] arrayOfContacts = getContactList();
		addressBookService = new AddressBookService(Arrays.asList(arrayOfContacts));
		Contact contact = addressBookService.getContact("Monica", "Geller");
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.delete("/contacts/" + contact.getContactId());
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		addressBookService.removeContact("Monica", "Geller");
		long entries = addressBookService.countEntries();
		assertEquals(5, entries);
	}

}
