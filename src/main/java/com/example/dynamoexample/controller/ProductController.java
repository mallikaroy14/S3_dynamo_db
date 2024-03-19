package com.example.dynamoexample.controller;


import com.example.dynamoexample.models.Products;
import com.example.dynamoexample.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    // To add Products in products table
    @PostMapping("/")
    public Products create(@RequestBody Products products){
        return productService.create(products);
    }

    // To show all Products
    @GetMapping("/")
    public List<Products> getAll(){
        return productService.getAll();
    }


    //TO get product on the basis of productId
    @GetMapping("/{id}")
    public Products getProductsById(@PathVariable String id){
        return productService.getProductsByID(id);

    }


    // To update product on the basis of id
    @PutMapping("/{id}")
    public String updateProducts(@PathVariable String id , @RequestBody Products products){
        return productService.updateProducts(id, products);
    }


    //To delete Product on the basis of id
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        return productService.deleteProducts(id);
    }

}
