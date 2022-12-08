package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
	
	@Id
	@Column(name = "id")
	Integer id;
	
	@Column(name = "countryname")
	String countryName;
	
	@Column(name = "countrycapital")
	String countryCapital;
	
	public Country() {
		
	}
	
	public Country(Integer id,String countryName,String countryCapital) {
		
		this.id = id;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapital() {
		return countryCapital;
	}
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}
		
}
