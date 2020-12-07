package com.adriel.challenge.customer.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.adriel.challenge.customer.dto.ForUpdateCustomerDto;
import com.adriel.challenge.customer.dto.NewCustomerDto;
import com.adriel.challenge.customer.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void shouldCreateCustomer() throws Exception {
		NewCustomerDto customer = NewCustomerDto.builder()
				.name("Jose")
				.cpf("61581615000")
				.address("N/A")
				.build();
		Customer expected = Customer.builder()
				.id(3L)
				.name("Jose")
				.cpf("61581615000")
				.address("N/A")
				.build();
		mvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isCreated())
				.andExpect(content().string(objectMapper.writeValueAsString(expected)));
	}
	
	@Test
	public void shouldFetchCustomer() throws Exception {
		Customer expected = Customer.builder()
				.id(1L)
				.name("Carlos")
				.cpf("98985646010")
				.address("17 September Street, number 123")
				.build();
		mvc.perform(get("/customer/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(expected)));
	}
	
	
	@Test
	public void shouldUpdateCustomer() throws Exception {
		ForUpdateCustomerDto customer = ForUpdateCustomerDto.builder()
				.id(2L)
				.name("Manuel")
				.cpf("80704555000")
				.address("N/A")
				.build();
		Customer expected = Customer.builder()
				.id(2L)
				.name("Manuel")
				.cpf("80704555000")
				.address("N/A")
				.build();
		mvc.perform(put("/customer/2").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer))).andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(expected)));
	}
	
	@Test
	public void shouldDeleteCustomer() throws Exception {
		mvc.perform(delete("/customer/2").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void shouldFetchAllCustomers() throws Exception {
		mvc.perform(get("/customer").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}
}
