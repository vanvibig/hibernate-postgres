package com.example.hibernatepostgres.service.impl;

import com.example.hibernatepostgres.model.User;
import com.example.hibernatepostgres.repository.UserRepository;
import com.example.hibernatepostgres.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

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
        System.out.println(String.format("get user by id %d",id));
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserInCache(Class<?> theClass, Serializable id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
        User user = (User) session.load(User.class, 1);
        System.out.println(user.getEmail());
        return user;
    }
}
