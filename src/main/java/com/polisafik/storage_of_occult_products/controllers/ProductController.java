package com.polisafik.storage_of_occult_products.controllers;

import com.polisafik.storage_of_occult_products.models.Product;
import com.polisafik.storage_of_occult_products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Create a new product.
     * @param product the product to create
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
    }

    /**
     * Update an existing product.
     * @param product the product to update
     * @return the updated product
     */
    @PutMapping
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    /**
     * Get a product by its ID.
     * @param id the UUID of the product
     * @return the product if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        return productService.getProduct(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * List all products.
     * @return the list of all products
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Delete a product by its ID.
     * @param id the UUID of the product
     * @return a response entity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
