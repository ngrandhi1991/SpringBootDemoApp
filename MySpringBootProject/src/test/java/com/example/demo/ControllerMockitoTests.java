package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.beans.Country;
import com.example.demo.controllers.CountryController;
import com.example.demo.services.CountryService;

@SpringBootTest(classes = {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	public void test_getCountries() {
		
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1,"India","NewDelhi"));
		mycountries.add(new Country(2,"USA","Washington DC"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries);
		ResponseEntity<List<Country>> res = countryController.getCountries();
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	
	@Test
	public void test_getCountryById() {
		
		country = new Country(2,"USA","Washington DC");
		
		Integer id = 2;
		
		when(countryService.getCountryById(id)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(id);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(id,res.getBody().getId());
				
	}
	
	@Test
	public void test_getCountryByName() {
		
		
		country = new Country(2,"USA","Washington DC");
		
		String name = "USA";
		
		when(countryService.getCountryByName(name)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(name);
		assertEquals(HttpStatus.OK,res.getStatusCode());
		assertEquals(name,res.getBody().getCountryName());
	}
	
	@Test
	public void test_addCountry() {
		
		country = new Country(2,"USA","Washington DC");
		
		when(countryService.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.addCountry(country);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country, res.getBody());
	}
	
	@Test
	public void test_updateCountry() {
		
		country = new Country(3,"Germany","Berlin");
		Integer id = 3;
		
		when(countryService.getCountryById(id)).thenReturn(country);
		
		when(countryService.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = countryController.updateCountry(id,country);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(country,res.getBody());
	}
	
	@Test
	public void test_deleteCountry() {
		
		country = new Country(3,"Germany","Berlin");
		Integer id = 3;
		
		when(countryService.getCountryById(id)).thenReturn(country);
		ResponseEntity<Country> res = countryController.deleteCountry(id);
		assertEquals(HttpStatus.OK,res.getStatusCode());
	}

}
