package com.webdev.spring.service;

import com.webdev.spring.domain.User;
import com.webdev.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RoleService roleService;

    public User findById(long id) {
        return this.repository.findById(id).get();
    }
    public List<User> findAll() {
        return this.repository.findAll();
    }
    public void delete(User user) {
        this.repository.delete(user);
    }
    public void update(User user) {
        User oldUser = this.repository.findById(user.getId()).get();
        if (user.getPassword().equals(oldUser.getPassword())) {
            this.repository.save(user);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            this.repository.save(user);
        }
    }
    public void add(User user) {
        if (user.getRoles().size() == 0) {
            user.getRoles().add(this.roleService.findByName("USER"));
        }
        user.setActive(1);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.repository.save(user);
    }

    public String encode(String value) {
        return bCryptPasswordEncoder.encode(value);
    }


    public boolean exists(long id) {
      return   this.repository.existsById(id);
    }
}
