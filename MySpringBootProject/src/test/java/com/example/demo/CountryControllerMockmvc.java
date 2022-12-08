package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.beans.Country;
import com.example.demo.controllers.CountryController;
import com.example.demo.services.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "com.example.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {CountryControllerMockmvc.class})
public class CountryControllerMockmvc {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@BeforeEach
	public void setup() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		mycountries = new ArrayList<>();
		mycountries.add(new Country(1,"India","NewDelhi"));
		mycountries.add(new Country(2,"USA","Washington DC"));
		
		when(countryService.getAllCountries()).thenReturn(mycountries);
		
		this.mockMvc.perform(get("/getcountries"))
			.andExpect(status().isOk())
			.andDo(print());
		
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
		
		country = new Country(3,"Germany","Berlin");
		Integer id =3;
		
		when(countryService.getCountryById(id)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/{id}",id))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(id))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value(country.getCountryName()))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value(country.getCountryCapital()))
			.andDo(print());
		
			
	}
	
	@Test
	@Order(3)
	public 	void test_getCountryByName() throws Exception {
		
		country = new Country(3,"Germany","Berlin");
		String name = "Germany";
		
		when(countryService.getCountryByName(name)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/countryname").param("countryName", name))
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath(".id").value(country.getId()))
					.andDo(print());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		
		country = new Country(3,"Germany","Berlin");
		
		when(countryService.addCountry(country)).thenReturn(country);
		
		ObjectMapper mapper = new ObjectMapper();
	}
}
