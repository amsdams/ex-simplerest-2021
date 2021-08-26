package com.amsdams.productregistration.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ProductDTO implements Serializable {

	private Long id;

	@NotNull
	@Size(max = 20)
	private String name;

	@NotNull
	@Size(max = 20)
	private String serialNumber;

	@NotNull
	private LocalDate purchased;

	@NotNull
	@Size(max = 20)
	private String firmware;

}
