package com.bridgelabz.AddressBookIO;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

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
	
	


}
