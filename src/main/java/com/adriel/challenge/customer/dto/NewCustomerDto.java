package com.adriel.challenge.customer.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewCustomerDto {
	private String name;
	@NotBlank
	@CPF
	private String cpf;
	private String address;
}
