package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Country;
import com.example.demo.controllers.AddResponse;
import com.example.demo.repositories.CountryRepository;

@Component //then only we can inject this class to another class
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRepository;
			
	public List<Country> getAllCountries() {
		
		return countryRepository.findAll();
	}
	
	public Country getCountryById(Integer id) {
		
		List<Country> countries = countryRepository.findAll();
		Country country = null;
		for(Country con : countries) {
			if(con.getId() == id)
				country = con;
		}
		return country;
	}
	
	public Country getCountryByName(String countryName){
		
		List<Country>countries = countryRepository.findAll();
		Country country = null;
		for(Country co : countries) {
			if(co.getCountryName().equalsIgnoreCase(countryName))
				country = co;
		}
		return country;
	}
	
	public Country addCountry(Country country) {
		
		country.setId(getMaxId());
		countryRepository.save(country);
		return country;
	}
	
	public Country updateCountry(Country country) {
		
		countryRepository.save(country);
		return country;
	}
	
	public void deleteCountry(Country country) {
		
		countryRepository.delete(country);
		
	}
	
	
	public int getMaxId() {
		return countryRepository.findAll().size()+1;
	}
}
