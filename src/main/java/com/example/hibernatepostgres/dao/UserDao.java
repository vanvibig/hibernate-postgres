package com.example.hibernatepostgres.dao;

import com.example.hibernatepostgres.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List getUserDetails();

    User createUser(User user);

    List getUserDetailsByCriteria();
}
