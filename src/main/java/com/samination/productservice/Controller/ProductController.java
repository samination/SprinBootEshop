package com.samination.productservice.Controller;

import com.samination.productservice.Model.Product;
import com.samination.productservice.Service.ProductService;
import com.samination.productservice.dto.GetProductDto;
import com.samination.productservice.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDto productDto){
            productService.createProduct(productDto);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductDto> getAllProducts()
    {
       return productService.getAllProducts();

    }
}
