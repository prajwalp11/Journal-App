package com.prajcompany.journal.controller;

import com.prajcompany.journal.entity.journalentry;
import com.prajcompany.journal.entity.user;
import com.prajcompany.journal.service.journalentryservice;
import com.prajcompany.journal.service.userservice;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalentrycontroller {
    @Autowired
    journalentryservice journalentryservice;

    @Autowired
    userservice userservice;
    @GetMapping("/get-all")
    public ResponseEntity<?> getallentriesofuser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        user user =userservice.getbyusername(username);
        List<journalentry> all =user.getEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-entry")
    public ResponseEntity<journalentry> addentry(@RequestBody  journalentry myentry)
    {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();
            journalentryservice.addentry(myentry,username);
            return new ResponseEntity<>(myentry,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // we must only give access to the journals of the logged-in user
    @ GetMapping("/id/{myid}")
    public ResponseEntity<journalentry> getbyid(@PathVariable ObjectId myid)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        user user=userservice.getbyusername(username);

        // getting all journal entries with the given id in the list
        List<journalentry> collect=user.getEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<journalentry> jj = journalentryservice.getbyid(myid);
            if(jj.isPresent())
            {
                return new ResponseEntity<>(jj.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId myid)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        journalentryservice.deletebyid(username,myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myid}")
    public ResponseEntity<?>  update(@PathVariable ObjectId myid, @RequestBody journalentry newentry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        user user=userservice.getbyusername(username);

        // getting all journal entries with the given id in the list
        List<journalentry> collect=user.getEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty())
        {
            Optional<journalentry> oldentry=journalentryservice.getbyid(myid);
            if(oldentry.isPresent())
            {
                oldentry.get().setTitle(oldentry.get().getTitle()!=null && !newentry.getTitle().equals("") ?newentry.getTitle(): oldentry.get().getTitle());
                oldentry.get().setContent(oldentry.get().getContent()!=null && !newentry.getContent().equals("") ?newentry.getContent(): oldentry.get().getContent());
                journalentryservice.addentry(oldentry.get());
                return new ResponseEntity<>(oldentry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     // controller -->service using dependency injection(autowired)
}
