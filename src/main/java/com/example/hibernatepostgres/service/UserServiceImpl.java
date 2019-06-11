package com.example.hibernatepostgres.service;

import com.example.hibernatepostgres.dao.UserDao;
import com.example.hibernatepostgres.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List getUserDetails() {
        return userDao.getUserDetails();
    }

    @Override
    public User createUser(User user) {
        return userDao.createUser(user);
    }

    @Override
    public List getUserDetailsByCriteria() {
        return userDao.getUserDetailsByCriteria();
    }
}
