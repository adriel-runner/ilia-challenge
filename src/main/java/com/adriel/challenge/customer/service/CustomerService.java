package com.adriel.challenge.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adriel.challenge.customer.entity.Customer;
import com.adriel.challenge.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository; 
	
	public CustomerService(CustomerRepository accountRepository) {
		this.customerRepository = accountRepository;
	}

	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer findById(Long id) {
		return customerRepository.findById(id).orElseThrow(CostumerNotExistsException::new);
	}

	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

}
