package com.webdev.spring.service;

import com.webdev.spring.domain.Producer;
import com.webdev.spring.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProducerService {
    @Autowired
    private ProducerRepository repository;

    public Producer save(Producer producer) {
        this.repository.save(producer);
        return producer;
    }
    public void delete(Producer producer) {
        this.repository.delete(producer);
    }
    public List<Producer> findAll() {
        return this.repository.findAll();
    }
    public Producer findById(long id) {
        return this.repository.findById(id).get();
    }

    public boolean exists(long id) {
       return this.repository.existsById(id);
    }
}
