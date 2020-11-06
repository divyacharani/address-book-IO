package com.bridgelabz.AddressBookIO;

import java.time.LocalDate;
import java.util.Objects;

public class Contact {
	// Attributes
	private int contactId;
	private String firstName;
	private String lastName;
	private String address;
	private String city; 
	private String state;
	private int zip;
	private long phoneNumber;
	private String email;
	private Integer addressBookId;
	private Integer addressBookTypeId; 
	private LocalDate createdDate;
	

	// Constructor
	public Contact(String firstName, String lastName, String address, String city, String state, int zip,
			long phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lastName; 
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Contact(int contactId , String firstName, String lastName, String address, String city, String state, int zip,
			long phoneNumber, String email) {
		this(firstName,lastName,address,city,state,zip,phoneNumber,email);
		this.contactId = contactId;
	}

	public Contact(String firstName, String lastName, String address, String city, String state, int zip,
			long phoneNumber, String email, LocalDate createdDate, int addressBookId, int addressBookTypeId) {
		this(firstName, lastName,address,city, state,zip,phoneNumber,email);
		this.createdDate = createdDate;
		this.addressBookId = addressBookId;
		this.addressBookTypeId = addressBookTypeId;
	}

	// Setters and Getters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public int getAddressBookId() {
		return addressBookId;
	}

	public void setAddressBookId(int addressBookId) {
		this.addressBookId = addressBookId;
	}

	public int getAddressBookTypeId() {
		return addressBookTypeId;
	}

	public void setAddressBookTypeId(int addressBookTypeId) {
		this.addressBookTypeId = addressBookTypeId;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return  firstName + " "+ lastName + " "+ address + " " + city
				+ " " + state + " " + zip + " " + phoneNumber + " " + email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (contactId != other.contactId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contactId,firstName,lastName);
	}
}
