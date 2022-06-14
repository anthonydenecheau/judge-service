package com.scc.judge.template;

import io.swagger.annotations.ApiModelProperty;

public class JudgeObject {

   @ApiModelProperty(notes = "Judge id", position = 1, allowEmptyValue = true)
   private int id;

   @ApiModelProperty(notes = "Judge civility", position = 2, allowEmptyValue = true)
   private String civility;

   @ApiModelProperty(notes = "Judge name", position = 3, allowEmptyValue = true)
   private String name;

   @ApiModelProperty(notes = "Judge Address", position = 4, allowEmptyValue = true)
   private String address;

   @ApiModelProperty(notes = "Judge zipCode", position = 5, allowEmptyValue = true)
   private String zipCode;

   @ApiModelProperty(notes = "Judge city", position = 6, allowEmptyValue = true)
   private String city;

   @ApiModelProperty(notes = "Judge email contact", position = 7, allowEmptyValue = true)
   private String email;

   @ApiModelProperty(notes = "Judge country", position = 8, allowEmptyValue = true)
   private String country;

   @ApiModelProperty(notes = "Judge number", position = 9, allowEmptyValue = true)
   private String number;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getCivility() {
      return civility;
   }

   public void setCivility(String civility) {
      this.civility = civility;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getZipCode() {
      return zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }
   
   public JudgeObject withId(int id) {
      this.setId(id);
      return this;
   }

   public JudgeObject withCivility(String civility) {
      this.setCivility(civility);
      return this;
   }

   public JudgeObject withName(String name) {
      this.setName(name);
      return this;
   }

   public JudgeObject withAddress(String address) {
      this.setAddress(address);
      return this;
   }

   public JudgeObject withZipCode(String zipCode) {
      this.setZipCode(zipCode);
      return this;
   }

   public JudgeObject withCity(String city) {
      this.setCity(city);
      return this;
   }

   public JudgeObject withCountry(String country) {
      this.setCountry(country);
      return this;
   }

   public JudgeObject withEmail(String email) {
      this.setEmail(email);
      return this;
   }

   public JudgeObject withNumber(String number) {
      this.setNumber(number);
      return this;
   }

}
