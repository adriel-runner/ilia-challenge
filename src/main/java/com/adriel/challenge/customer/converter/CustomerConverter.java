package com.adriel.challenge.customer.converter;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.adriel.challenge.customer.dto.CreatedCustomerDto;
import com.adriel.challenge.customer.dto.CustomerDto;
import com.adriel.challenge.customer.dto.NewCustomerDto;
import com.adriel.challenge.customer.dto.UpdatedCustomerDto;
import com.adriel.challenge.customer.entity.Customer;

public class CustomerConverter {
	public static Customer toCustomer(NewCustomerDto newCustomerDto) {
		return Customer.builder().name(newCustomerDto.getName()).cpf(newCustomerDto.getCpf())
				.address(newCustomerDto.getAddress()).build();
	}

	public static CreatedCustomerDto toCreatedCustomerDto(Customer customer) {
		return CreatedCustomerDto.builder().id(customer.getId()).name(customer.getName()).cpf(customer.getCpf())
				.address(customer.getAddress()).build();
	}

	public static UpdatedCustomerDto toUpdatedCustomerDto(Customer customer) {
		return UpdatedCustomerDto.builder().id(customer.getId()).name(customer.getName()).cpf(customer.getCpf())
				.address(customer.getAddress()).build();
	}

	public static CustomerDto toCustomerDto(Customer customer) {
		return CustomerDto.builder().id(customer.getId()).name(customer.getName()).cpf(customer.getCpf())
				.address(customer.getAddress()).build();
	}

	public static Iterable<CustomerDto> toListCustomerDto(Iterable<Customer> customers) {
		return StreamSupport.stream(customers.spliterator(), false)
				.map(CustomerConverter::toCustomerDto)
				.collect(Collectors.toList());
	}
}
