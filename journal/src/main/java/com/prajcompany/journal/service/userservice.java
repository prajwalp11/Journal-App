package com.prajcompany.journal.service;

import com.prajcompany.journal.entity.user;
import com.prajcompany.journal.repository.userrepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Component
public class userservice {

    @Autowired
    userrepository userrepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
public boolean adduser(user myentry)
    {
        userrepository.save(myentry);
        return true;
    }
    public boolean adduserencoded(user myentry)
    {
        myentry.setPassword(passwordEncoder.encode(myentry.getPassword()));
        myentry.setRoles(Arrays.asList("USER"));
        userrepository.save(myentry);
        return true;
    }
    public List<user> getall()
    {
        return userrepository.findAll();
    }
    public user getbyusername(String username)
    {
        return userrepository.findByUsername(username);
    }

    public void deletebyusername(ObjectId myid) {
    userrepository.deleteById(myid);
    }

    public boolean addadminencoded(user myentry) {
        myentry.setPassword(passwordEncoder.encode(myentry.getPassword()));
        myentry.setRoles(Arrays.asList("USER","ADMIN"));
        user user=userrepository.save(myentry);
        if(user==null)
            return false;
        return true;
    }
}
