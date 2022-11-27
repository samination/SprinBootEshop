package com.samination.productservice.Service;

import com.samination.productservice.Model.Product;
import com.samination.productservice.Repository.ProductRepository;
import com.samination.productservice.dto.GetProductDto;
import com.samination.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductDto productDto){

        Product product=Product.builder().name(productDto.getName()).description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product"+product.getId()+ "Created");

    }

    public List<GetProductDto> getAllProducts() {
        List<Product> products=productRepository.findAll();

        return  products.stream().map(this::mapToProductResponse).toList();

    }

    private GetProductDto mapToProductResponse(Product product) {
        return GetProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
