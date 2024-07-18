package com.ecommerce.shop.controller;

import com.ecommerce.shop.entity.Product;
import com.ecommerce.shop.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

//    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productRepository.findAll();

        if(products != null && !products.isEmpty()){
            return ResponseEntity.ok(products);
        }
        return ResponseEntity.notFound().build();
    }

//    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productRepository.save(product)
        );
    }


}
