package com.bridgelabz.AddressBookIO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import com.bridgelabz.AddressBookIO.DatabaseException.exceptionType;

public class DBConnection {
	// To load drivers and establish connection
	public static Connection getConnection() throws DatabaseException {
		Connection connection = null;
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String dataBaseURL = "jdbc:mysql://localhost:3306/address_book_service";

		// Load driver class
		try {
			Class.forName(jdbcDriver);
			AddressBookService.LOG.info("Driver Loaded!!!");
		} catch (ClassNotFoundException e) {
			throw new DatabaseException("Unable to load the driver!!", exceptionType.DRIVER_CONNECTION);
		}

		AddressBookService.LOG.info("Connecting to....." + dataBaseURL);

		// Establish Connection
		try {
			connection = DriverManager.getConnection(dataBaseURL, "root", "Charani@19");
			AddressBookService.LOG.info("Connection established successfully!!!");
		} catch (SQLException e) {
			throw new DatabaseException("Unable to connect to database!!", exceptionType.DATABASE_CONNECTION);
		}
		return connection;
	}

	// To get list of drivers
	private static void listDrivers() {
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		AddressBookService.LOG.info("Drivers List : ");
		while (driverList.hasMoreElements()) {
			Driver driverClass = driverList.nextElement();
			AddressBookService.LOG.info(driverClass.getClass().getName());
		}
	}
}
