package com.scc.judge.template;

import io.swagger.annotations.ApiModelProperty;

public class JudgeObject {

	@ApiModelProperty(notes = "Judge id", position = 1, allowEmptyValue=true)
	private int id;
	
	@ApiModelProperty(notes = "Judge name", position = 2, allowEmptyValue=true)
	private String name;

	@ApiModelProperty(notes = "Judge Address", position = 3, allowEmptyValue=true)
	private String address;
	
	@ApiModelProperty(notes = "Judge ZipCode", position = 4, allowEmptyValue=true)
	private String zipCode;

	@ApiModelProperty(notes = "Judge City", position = 5, allowEmptyValue=true)
	private String city;
	
	@ApiModelProperty(notes = "Judge email contact", position = 6, allowEmptyValue=true)
	private String email;
	
	@ApiModelProperty(notes = "Judge Country", position = 7, allowEmptyValue=true)
	private String country;

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
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
	
	public JudgeObject withId(int id){ this.setId( id ); return this; }
	public JudgeObject withName(String name){ this.setName(name); return this; }
	public JudgeObject withAddress(String address){ this.setAddress(address); return this; }
	public JudgeObject withZipCode(String zipCode){ this.setZipCode(zipCode); return this; }
	public JudgeObject withCity(String city){ this.setCity(city); return this; }
	public JudgeObject withCountry(String country){ this.setCountry(country); return this; }
	public JudgeObject withEmail(String email){ this.setEmail(email); return this; }

}
