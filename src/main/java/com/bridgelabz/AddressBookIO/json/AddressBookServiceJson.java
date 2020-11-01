package com.bridgelabz.AddressBookIO.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.bridgelabz.AddressBookIO.Contact;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class AddressBookServiceJson {

	public static final String PATH_JSON = "C:\\Users\\DELL\\eclipse-workspace\\AddressBookIO\\src\\main\\java\\com\\bridgelabz\\AddressBookIO\\json\\addressbook-json.json";

	// To read data from json file
	public void readDataJSON() {

		try {

			Contact contact = new Gson().fromJson(new FileReader(PATH_JSON), Contact.class);
			System.out.println(contact);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// To write data into json file
	public void writeDataJSON(Contact contact) {

		try {
			FileWriter writer = new FileWriter(PATH_JSON);
			new Gson().toJson(contact, writer);
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}
}
