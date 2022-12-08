package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.beans.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;


@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	@Mock
	CountryRepository countryRep;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> mycountries;
	
	@Test
	public void test_getAllCountries() {
		
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1,"India","NewDelhi"));
		mycountries.add(new Country(2,"Test","TestCapital"));
		
		when(countryRep.findAll()).thenReturn(mycountries);//mocking
		assertEquals(2,countryService.getAllCountries().size());
	}
	
	@Test
	public void test_getCountryById() {
		
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1,"India","NewDelhi"));
		mycountries.add(new Country(2,"Test","TestCapital"));
		int id = 2;
		
		when(countryRep.findAll()).thenReturn(mycountries);
		Country country = countryService.getCountryById(id);
		assertEquals(id,country.getId());
				
	}
	
	@Test
	public void test_getCountryByName() {
		
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1,"India","NewDelhi"));
		mycountries.add(new Country(2,"Test","TestCapital"));
		String name = "India";
		
		when(countryRep.findAll()).thenReturn(mycountries);
		Country country = countryService.getCountryByName(name);
		assertEquals(name,country.getCountryName());
	}
	
	@Test
	public void test_addCountry() {
		
		Country country = new Country(3,"USA","Washignton");
		
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country,countryService.addCountry(country));
	}
	
	@Test
	public void test_updateCountry() {
		
		Country country = new Country(3,"USA","Washignton");
		country.setCountryCapital("Washington DC");
		
		when(countryRep.save(country)).thenReturn(country);
		assertEquals(country,countryService.updateCountry(country));
	}
	
	@Test
	public void test_deleteCountry() {
		
		Country country = new Country(3,"USA","Washignton DC");
		countryService.deleteCountry(country);
		verify(countryRep,times(1)).delete(country);
		
	}

}
