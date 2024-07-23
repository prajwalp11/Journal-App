package com.prajcompany.journal.service;

import com.prajcompany.journal.entity.journalentry;
import com.prajcompany.journal.entity.user;
import com.prajcompany.journal.repository.journalentryrepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalentryservice {
    
    @Autowired
    journalentryrepository journalentryrepository;
    @Autowired
    userservice userservice;
@Transactional
public boolean addentry(journalentry myentry,String username)
    {
        user user=userservice.getbyusername(username);
        myentry.setDate(LocalDateTime.now());
        journalentry saved =journalentryrepository.save(myentry);
        user.getEntries().add(saved);
//      user.setUsername(null);     //to check transaction
        userservice.adduser(user);
        return true;
    }
    public boolean addentry(journalentry myentry)
    {
        journalentryrepository.save(myentry);
        return true;
    }
    public List<journalentry> getall()
    {
        return journalentryrepository.findAll();
    }
    public Optional<journalentry> getbyid(ObjectId myid)
    {
        return journalentryrepository.findById(myid);
    }
@Transactional
    public void deletebyid(String username,ObjectId myid) {
    try {
        user user = userservice.getbyusername(username);
        boolean removed = user.getEntries().removeIf((x) -> {
            return x.getId().equals(myid);
        });
        if (removed) {
            userservice.adduser(user);
            journalentryrepository.deleteById(myid);
        }
    } catch (Exception e) {
        throw new RuntimeException("Error occurred while deleting"+e);
    }
}

//    public List<journalentry> findentriesbyusername(String username)
//    {
//
//    }
    // service --> repository( autowired -dependency injection)
}
