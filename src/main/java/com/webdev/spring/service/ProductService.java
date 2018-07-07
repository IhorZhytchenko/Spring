package com.webdev.spring.service;


import com.webdev.spring.domain.Product;
import com.webdev.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        this.repository.save(product);
        return product;
    }
    public void delete(Product product) {
        this.repository.delete(product);
    }
    public List<Product> findAll() {
        return this.repository.findAll();
    }
    public Product findById(long id) {
        return this.repository.findById(id).get();
    }
    public boolean exists(long id) {
        return this.repository.existsById(id);
    }

}
