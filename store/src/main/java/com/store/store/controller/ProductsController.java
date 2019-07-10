package com.store.store.controller;


import com.store.store.model.ProductsBean;
import com.store.store.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping("/")
    public ResponseEntity<List<ProductsBean>> getProducts() {

        return new ResponseEntity<>((List<ProductsBean>) productsRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Void> postProduct(@RequestBody ProductsBean product) {
        productsRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductsBean> getProduct(@PathVariable("id") String id){
        return new ResponseEntity<>(productsRepository.findById(id).get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") String id, @RequestBody ProductsBean productsBean){
        productsBean.setId(id);
        productsRepository.save(productsBean);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
