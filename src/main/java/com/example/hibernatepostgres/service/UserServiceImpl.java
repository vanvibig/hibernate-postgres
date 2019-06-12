package com.example.hibernatepostgres.service;

import com.example.hibernatepostgres.model.User;
import com.example.hibernatepostgres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List getUserDetails() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List getUserDetailsByCriteria() {
        return userRepository.getUserDetailsByCriteria();
    }

    @Override
    public User getUserBYId(int id) {
        return userRepository.findById(id).get();
    }
}
