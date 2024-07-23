package com.prajcompany.journal.controller;

import java.net.http.HttpResponse;
import java.util.List;

import com.prajcompany.journal.entity.user;
import com.prajcompany.journal.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    userservice userservice;

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getallusers()
    {
        try {
            List<user> user=userservice.getall();
            if(user.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> addadmin(@RequestBody user user)
    {
        boolean bool =userservice.addadminencoded(user);
        if(bool)
        return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
