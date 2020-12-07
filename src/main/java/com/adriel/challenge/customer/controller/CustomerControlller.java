package com.adriel.challenge.customer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adriel.challenge.customer.converter.CustomerConverter;
import com.adriel.challenge.customer.dto.CreatedCustomerDto;
import com.adriel.challenge.customer.dto.CustomerDto;
import com.adriel.challenge.customer.dto.ForUpdateCustomerDto;
import com.adriel.challenge.customer.dto.NewCustomerDto;
import com.adriel.challenge.customer.dto.UpdatedCustomerDto;
import com.adriel.challenge.customer.entity.Customer;
import com.adriel.challenge.customer.service.CustomerCpfChangingException;
import com.adriel.challenge.customer.service.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("customer")
public class CustomerControlller {
	private final CustomerService customerService;
	
	public CustomerControlller(CustomerService accountService) {
		super();
		this.customerService = accountService;
	}

	@ApiOperation(value = "Adds a new customer", response = Customer.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Customer created successfully"),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreatedCustomerDto create(@Valid @RequestBody NewCustomerDto newCustomerDto) {
		Customer customer = CustomerConverter.toCustomer(newCustomerDto);
		customerService.save(customer);
		return CustomerConverter.toCreatedCustomerDto(customer);
	}
	
	@ApiOperation(value = "Fetches a customer by id", response = Customer.class)
	@GetMapping("{id}")
	public Customer get(@PathVariable Long id) {
		return customerService.findById(id);
	}
	
	@ApiOperation(value = "Updates a customer", response = Customer.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer updated successfully"),
            @ApiResponse(code = 404, message = "Customer not found"),
            @ApiResponse(code = 406, message = "CPF cannot be changed"),
    })
	@PutMapping("{id}")
	public UpdatedCustomerDto update(@PathVariable Long id, @Valid @RequestBody ForUpdateCustomerDto forUpdateCustomerDto) {
		Customer foundCustomer = customerService.findById(id);
		if (!foundCustomer.getCpf().equals(forUpdateCustomerDto.getCpf())) {
			throw new CustomerCpfChangingException("CPF cannot be change");
		}
		foundCustomer.setName(forUpdateCustomerDto.getName());
		foundCustomer.setCpf(forUpdateCustomerDto.getCpf());
		foundCustomer.setAddress(forUpdateCustomerDto.getAddress());
		return CustomerConverter.toUpdatedCustomerDto(customerService.update(foundCustomer));
	}
	
	@ApiOperation(value = "Deletes a customer")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer deleted successfully"),
            @ApiResponse(code = 404, message = "Customer not found"),
    })
    @DeleteMapping(value = "{id}")
    public void remove(@PathVariable(value = "id") long id) {
        customerService.findById(id);
        customerService.delete(id);
    }
	
	@ApiOperation(value = "Fetches all customer")
    @GetMapping
    public Iterable<CustomerDto> list() {
        return CustomerConverter.toListCustomerDto(customerService.findAll());
    }
	
}
