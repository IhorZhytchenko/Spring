package com.webdev.spring.service;

import com.webdev.spring.domain.Role;
import com.webdev.spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public List<Role> findAll() {
        return this.repository.findAll();
    }
    public Role findById(long id) {
        return this.repository.findById(id).get();
    }
    public Role findByName(String name) {
       return this.repository.findByRole(name);
    }
}
