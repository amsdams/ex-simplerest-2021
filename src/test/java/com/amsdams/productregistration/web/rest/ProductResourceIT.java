/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amsdams.productregistration.web.rest;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.amsdams.productregistration.ProductRegistrationApplication;
import com.amsdams.productregistration.domain.Product;
import com.amsdams.productregistration.repository.ProductRepository;
import com.amsdams.productregistration.service.ProductService;
import com.amsdams.productregistration.service.mapper.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = ProductRegistrationApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@Slf4j
class ProductResourceIT {
	private static final String DEFAULT_NAME = "AAAAAAAAAA";
	private static final String UPDATED_NAME = "BBBBBBBBBB";

	private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
	private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

	private static final LocalDate DEFAULT_PURCHASED = LocalDate.ofEpochDay(0L);
	private static final LocalDate UPDATED_PURCHASED = LocalDate.now(ZoneId.systemDefault());

	private static final String DEFAULT_FIRMWARE = "AAAAAAAAAA";
	private static final String UPDATED_FIRMWARE = "BBBBBBBBBB";

	private static final String ENTITY_API_URL = "/api/products";
	private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

	// @Autowired
	// private TestEntityManager entityManager;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private EntityManager em;
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductMapper productMapper;

	private Product product;

	public static Product createEntity(EntityManager em) {

		Product product = new Product().withName(DEFAULT_NAME).withSerialNumber(DEFAULT_SERIAL_NUMBER)
				.withPurchased(DEFAULT_PURCHASED).withFirmware(DEFAULT_FIRMWARE);
		return product;
	}

	@BeforeEach
	public void initTest() {
		product = createEntity(em);
	}

	@Test
	void testFindByName() throws Exception {
		productRepository.saveAndFlush(product);
		// int databaseSizeBeforeUpdate = productRepository.findAll().size();
		// ProductDTO productDTO = productMapper.toDto(product);

		this.mockMvc.perform(get(ENTITY_API_URL + "/name={name}", DEFAULT_NAME)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
				.andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
				.andExpect(jsonPath("$.[*].purchased").value(hasItem(DEFAULT_PURCHASED.toString())))
				.andExpect(jsonPath("$.[*].firmware").value(hasItem(DEFAULT_FIRMWARE)));

		// List<Product> findByLastName = controller.findByName(DEFAULT_NAME);
		// assertThat(findByLastName).extracting(Product::getName).containsOnly(product.getName());
	}

	@Test
	void testFindAll() throws Exception {
		productRepository.saveAndFlush(product);
		// int databaseSizeBeforeUpdate = productRepository.findAll().size();
		this.mockMvc.perform(get(ENTITY_API_URL + "/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[*].id").value(hasItem(product.getId().intValue())))
				.andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
				.andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER)))
				.andExpect(jsonPath("$.[*].purchased").value(hasItem(DEFAULT_PURCHASED.toString())))
				.andExpect(jsonPath("$.[*].firmware").value(hasItem(DEFAULT_FIRMWARE)));
		// List<Product> findByLastName = controller.findByName(DEFAULT_NAME);
		// assertThat(findByLastName).extracting(Product::getName).containsOnly(product.getName());
	}

}
