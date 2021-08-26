package com.amsdams.productregistration.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Data
@NoArgsConstructor
@With
@AllArgsConstructor
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String serialNumber;
	private LocalDate purchased;
	private String firmware;

	public Product(String name, String serialNumber, LocalDate purchased, String firmware) {
		this.name = name;
		this.serialNumber = serialNumber;
		this.purchased = purchased;
		this.firmware = firmware;
	}

}
