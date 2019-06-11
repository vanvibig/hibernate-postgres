package com.example.hibernatepostgres.controller;

import com.example.hibernatepostgres.model.User;
import com.example.hibernatepostgres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> userDetails() {
        List usersDetail = userService.getUserDetails();
        return new ResponseEntity<>(usersDetail, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User res = userService.createUser(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
