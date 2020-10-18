package com.bridgelabz.AddressBookIO;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class Main {

	public static final String path = "C:\\Users\\DELL\\eclipse-workspace\\AddressBookIO\\src\\main\\java\\com\\bridgelabz\\AddressBookIO\\addressbook-file.csv";
	List<Contact> contactList = new ArrayList<Contact>();

	public static void main(String[] args) {

		List<Contact> contactList = new ArrayList<Contact>();
		Scanner sc = new Scanner(System.in);

		// Welcome Message
		System.out.println("Welcome to Address Book Program ");

		Main main = new Main();
		Contact contact = main.getContact(sc);
		contactList.add(contact);
		main.writeData(contact);
		main.readData();

	}

	// To get contact from console
	private Contact getContact(Scanner sc) {
		System.out.println("Enter First Name");
		String firstName = sc.next();
		System.out.println("Enter Last Name");
		String lastName = sc.next();
		System.out.println("Enter Address");
		String address = sc.next();
		System.out.println("Enter City");
		String city = sc.next();
		System.out.println("Enter State");
		String state = sc.next();
		System.out.println("Enter Zip");
		int zip = sc.nextInt();
		System.out.println("Enter Phone Number");
		long phoneNumber = sc.nextLong();
		System.out.println("Enter email");
		String email = sc.next();
		return (new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email));
	}

	// To write data into file
	private void writeData(Contact contact) {

		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(path));
			String[] header = { "First Name", "Last Name", "Address", "City", "State", "Zip", "Phone Number", "Email" };
			writer.writeNext(header);
			String[] info = contact.toString().split(" ");
			writer.writeNext(info);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To read data into file
	private void readData() {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(path));
			String[] nextRecord;
			while ((nextRecord = reader.readNext()) != null) {
				for(String record : nextRecord) {
					System.out.print(record+' ');
				}
				System.out.println();
			}
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
