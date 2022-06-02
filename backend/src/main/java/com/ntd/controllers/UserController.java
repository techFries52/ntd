package com.ntd.controllers;

import com.ntd.models.User;
import com.ntd.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserService uServ;

    @PostMapping
    public ResponseEntity<User> saveNewUser(@RequestBody User newUser){
        ResponseEntity<User> response = null;

        if(uServ.ifUserExists(newUser.getUser_id())){
            // user already exists
            response = new ResponseEntity<User>(newUser, HttpStatus.CONFLICT); // 409
        } else {
            // user does not already exist
            uServ.saveUser(newUser);
            response = new ResponseEntity<User>(newUser, HttpStatus.CREATED); // 201
        }
        return response;
    }

}
