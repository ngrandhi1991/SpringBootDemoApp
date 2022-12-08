package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Country;
import com.example.demo.services.CountryService;

@RestController
public class CountryController {
	
	@Autowired //No need to create object spring boot directly talks to service methods(Dependency Injection)
	CountryService countryService;
	
	@GetMapping("/getcountries")
	public ResponseEntity<List<Country>> getCountries(){
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<>(countries,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id") Integer id) {
		
		try {
			Country country = countryService.getCountryById(id);
			return new ResponseEntity<>(country,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam String countryName) {
		
		try {
			Country country = countryService.getCountryByName(countryName);
			return new ResponseEntity<>(country,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		
		try {
			country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country,HttpStatus.CREATED);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable (value = "id")Integer id,@RequestBody Country country) {
		try {
			Country countryExists = countryService.getCountryById(id);
			countryExists.setCountryName(country.getCountryName());
			countryExists.setCountryCapital(country.getCountryCapital());
			Country countryUpdated = countryService.updateCountry(countryExists);
			return new ResponseEntity<>(countryUpdated,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable(value = "id") Integer Id) {
		Country country = null;
		try {
			country = countryService.getCountryById(Id);
			countryService.deleteCountry(country);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
		return new ResponseEntity<>(country,HttpStatus.OK);
	}
}
