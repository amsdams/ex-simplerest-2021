package com.amsdams.productregistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amsdams.productregistration.domain.Product;
import com.amsdams.productregistration.repository.ProductRepository;

@SpringBootApplication
public class ProductRegistrationApplication {

	private static final Logger log = LoggerFactory.getLogger(ProductRegistrationApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProductRegistrationApplication.class);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// save a few Products
			repository.save(new Product("EOS Rebel T4i", "053053001911", null, "1.0.5"));
			repository.save(new Product("EF-S10-18mm F4.5-5.6 IS STM", "3822004235", null, null));
			repository.save(new Product("EF35mm F2 IS USM", "6440000454", null, "  1.0.0"));
			repository.save(new Product("EF-S18-135mm F3.5-5.6 IS STM", "4642002712", null, " 1.5.0"));
			repository.save(new Product("EF100mm F2.8 Macro USM", null, null, " 1.0.0"));
			repository.save(new Product("EF50mm F1.4 USM", "9001102753", null, null));

			// fetch all Products
			log.info("Products found with findAll():");
			log.info("-------------------------------");
			for (Product customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Product customer = repository.findById(1L).get();
			log.info("Product found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch Products by last name
			log.info("Product found with findByName('EOS Rebel T4i'):");
			log.info("--------------------------------------------");
			repository.findByName("EOS Rebel T4i").forEach(bauer -> log.info(bauer.toString()));
			// for (Product bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
