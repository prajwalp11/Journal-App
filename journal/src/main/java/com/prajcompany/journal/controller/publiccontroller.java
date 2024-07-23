package com.prajcompany.journal.controller;

import com.prajcompany.journal.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/public")
@RestController
public class publiccontroller {

    @Autowired
    com.prajcompany.journal.service.userservice userservice;

    @GetMapping("/health-check")
    public String health(){
       return "OK";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> addentry(@RequestBody user myentry)
    {
        try {
            userservice.adduserencoded(myentry);
            return new ResponseEntity<>(myentry, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
