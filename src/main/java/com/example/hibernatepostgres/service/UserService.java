package com.example.hibernatepostgres.service;

import com.example.hibernatepostgres.model.User;

import java.util.List;

public interface UserService {

    List getUserDetails();

    User createUser(User user);

    List getUserDetailsByCriteria();
}
