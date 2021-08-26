package com.amsdams.productregistration.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amsdams.productregistration.service.ProductService;
import com.amsdams.productregistration.service.dto.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductResource {
	@Autowired
	private ProductService productService;

	@GetMapping("/{id}")
	public ProductDTO findById(@PathVariable long id) {
		log.debug("REST request to get Product : {}", id);
		Optional<ProductDTO> productDTO = productService.findById(id);
		return productDTO.get();

	}

	@GetMapping("/")
	public List<ProductDTO> findAll() {
		return productService.findAll();
	}

	@GetMapping("/name={name}")
	public List<ProductDTO> findByName(@PathVariable("name") final String name) {
		return productService.findByName(name);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO update(@PathVariable("id") final String id, @RequestBody final ProductDTO product) {
		return product;
	}
}
