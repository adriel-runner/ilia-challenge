package com.adriel.challenge.customer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
	private Long id;
	private String name;
	private String cpf;
	private String address;
}
