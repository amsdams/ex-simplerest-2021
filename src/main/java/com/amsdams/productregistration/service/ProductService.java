package com.amsdams.productregistration.service;

import java.util.List;
import java.util.Optional;

import com.amsdams.productregistration.service.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> findAll();

	Optional<ProductDTO> findById(long id);

	List<ProductDTO> findByName(String name);

	ProductDTO save(ProductDTO productDTO);

	void deleteAll();

}