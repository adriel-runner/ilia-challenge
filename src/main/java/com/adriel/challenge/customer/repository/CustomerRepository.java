package com.adriel.challenge.customer.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.adriel.challenge.customer.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{

}
