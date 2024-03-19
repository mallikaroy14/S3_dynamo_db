package com.example.dynamoexample.services;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.example.dynamoexample.models.Products;
import com.example.dynamoexample.models.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Products create(Products products) {
        dynamoDBMapper.save(products);
        return products;
    }

    public List<Products> getAll() {
        List<Products> products = dynamoDBMapper.scan(Products.class, new DynamoDBScanExpression());
        return products;
    }

    public Products getProductsByID(String id) {
        Products products = dynamoDBMapper.load(Products.class, id);
        return products;
    }

    public String updateProducts(String id, Products products) {
        Products singleProducts = dynamoDBMapper.load(Products.class, id);
        if (singleProducts.getId() != null) {
            singleProducts.setName(products.getName());
            singleProducts.setPrice(products.getPrice());

            dynamoDBMapper.save(singleProducts);

            return "Products are updated";
        } else {
            return "Products with this is not present";
        }
    }

    public String deleteProducts(String id) {
        Products products = dynamoDBMapper.load(Products.class, id);
        if (products.getId() != null) {
            dynamoDBMapper.delete(products);
            return "Product is deleted successfully";
        }
        return "Product id with this id is not present";
    }

}
