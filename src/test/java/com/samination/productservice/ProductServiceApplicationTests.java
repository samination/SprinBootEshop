package com.samination.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.samination.productservice.Repository.ProductRepository;
import com.samination.productservice.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry)
	{
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);

	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductDto productDto=getProductRequest();
	String productRequestString=objectMapper.writeValueAsString(productDto);
	mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(productRequestString))
			.andExpect(status().isCreated());

		Assertions.assertEquals(1, productRepository.findAll().size());

	}

	@Test
	void shouldGetProducts() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")).andExpect(status().isOk());
		Assertions.assertTrue(productRepository.findAll().size()==1);

	}

	private ProductDto getProductRequest() {

		return ProductDto.builder()
				.name("iPhone X")
				.description("Apple Product")
				.price(BigDecimal.valueOf(950))
				.build();
	}

}
