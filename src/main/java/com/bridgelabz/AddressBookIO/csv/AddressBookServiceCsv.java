package com.bridgelabz.AddressBookIO.csv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.bridgelabz.AddressBookIO.Contact;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBookServiceCsv {
	public static final String PATH_CSV = "C:\\Users\\DELL\\eclipse-workspace\\AddressBookIO\\src\\main\\java\\com\\bridgelabz\\AddressBookIO\\csv\\addressbook-file.csv";

	// To write data into csv file
	public void writeDataCSV(Contact contact) {

		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(PATH_CSV));
			String[] header = { "First Name", "Last Name", "Address", "City", "State", "Zip", "Phone Number", "Email" };
			writer.writeNext(header);
			String[] info = contact.toString().split(" ");
			writer.writeNext(info);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// To read data from csv file
	public void readDataCSV() {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(PATH_CSV));
			String[] nextRecord;
			while ((nextRecord = reader.readNext()) != null) {
				for (String record : nextRecord) {
					System.out.print(record + ' ');
				}
				System.out.println();
			}
			reader.close();
		} catch (IOException | CsvValidationException e) {
			e.printStackTrace();
		}
	}
}
