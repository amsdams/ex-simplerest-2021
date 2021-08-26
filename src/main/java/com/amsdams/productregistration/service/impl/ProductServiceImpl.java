package com.amsdams.productregistration.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amsdams.productregistration.domain.Product;
import com.amsdams.productregistration.repository.ProductRepository;
import com.amsdams.productregistration.service.ProductService;
import com.amsdams.productregistration.service.dto.ProductDTO;
import com.amsdams.productregistration.service.mapper.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Override
	public ProductDTO save(ProductDTO productDTO) {
		log.debug("Request to save Product : {}", productDTO);
		Product product = productMapper.toEntity(productDTO);
		product = productRepository.save(product);
		return productMapper.toDto(product);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		log.debug("Request to get all Products");
		return productRepository.findAll().stream().map(productMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProductDTO> findById(long id) {
		log.debug("Request to get Product : {}", id);
		return productRepository.findById(id).map(productMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDTO> findByName(String name) {
		return productRepository.findByName(name).stream().map(productMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));

	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();

	}

}
