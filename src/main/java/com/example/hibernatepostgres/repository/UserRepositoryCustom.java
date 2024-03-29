package com.example.hibernatepostgres.repository;

import com.example.hibernatepostgres.model.User;

import java.util.List;

public interface UserRepositoryCustom {

    List getUserDetails();

    User createUser(User user);

    List getUserDetailsByCriteria();
}
