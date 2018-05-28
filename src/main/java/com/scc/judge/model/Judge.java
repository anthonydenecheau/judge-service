package com.scc.judge.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ws_juge_expos")
public class Judge{

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "num")
	private String number;

	@Column(name = "civilite")
	private String civility;

	@Column(name = "nom")
	private String lastName;

	@Column(name = "prenom")
	private String firstName;
	
	@Column(name = "adresse")
	private String address;

	@Column(name = "code_postal")
	private String zipCode;

	@Column(name = "ville")
	private String city;

	@Column(name = "pays")
	private String country;

	@Column(name = "mail")
	private String email;

	@Column(name = "on_international")
	private String isInternational;
	
	@Column(name = "date_maj")
	private Timestamp timestamp;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getNumber() { return number; }
	public void setNumber(String number) { this.number = number; }
	
	public String getCivility() { return civility; }
	public void setCivility(String civility) { this.civility = civility; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }
	
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getIsInternational() { return isInternational; }
	public void setIsInternational(String isInternational) { this.isInternational = isInternational; }

	public Timestamp getTimestamp() { return timestamp; }
	public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }

	public Judge withId(int id){ this.setId( id ); return this; }
	public Judge withNumber(String number){ this.setNumber(number); return this; }
	public Judge withCivility(String civility){ this.setCivility(civility); return this; }
	public Judge withLastName(String lastName){ this.setLastName(lastName); return this; }
	public Judge withFirstName(String firstName){ this.setFirstName(firstName); return this; }
	public Judge withAddress(String address){ this.setAddress(address); return this; }
	public Judge withZipCode(String zipCode){ this.setZipCode(zipCode); return this; }
	public Judge withCity(String city){ this.setCity(city); return this; }
	public Judge withCountry(String country){ this.setCountry(country); return this; }
	public Judge withEmail(String email){ this.setEmail(email); return this; }
	public Judge withIsInternational(String isInternational){ this.setIsInternational( isInternational ); return this; }
	public Judge withTimestamp(Timestamp timestamp){ this.setTimestamp(timestamp); return this; }


}
