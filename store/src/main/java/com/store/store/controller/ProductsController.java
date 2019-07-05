package com.store.store.controller;


import com.store.store.model.ProductsBean;
import com.store.store.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProductsBean>> getProducts() {

        return new ResponseEntity<List<ProductsBean>>((List<ProductsBean>) productsRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Void> postProduct(@RequestBody ProductsBean product) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
