package com.prajcompany.journal.controller;

import com.prajcompany.journal.entity.user;
import com.prajcompany.journal.repository.userrepository;
import com.prajcompany.journal.service.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class usercontroller {
    @Autowired
    userservice userservice;


    //autowiring repo directly not preferred but fast
    @Autowired
    userrepository userrepository;

    //no need for getall its a admin functionality

//    @GetMapping("/getall")
//    public ResponseEntity<?> getall()
//    {
//        List<user> all = userservice.getall();
//        if(all!=null && !all.isEmpty())
//        {
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


//    @ GetMapping("/{username}")
//    public ResponseEntity<?> getbyid(@PathVariable String username)
//    {
//        user jj = userservice.getbyusername(username);
//        if(jj!=null)
//        {
//            return new ResponseEntity<>(jj,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }



    @PutMapping("/edit-user")
    public ResponseEntity<?>  update(@RequestBody user newentry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();;
        user oldentry=userservice.getbyusername(username);
        if(oldentry!=null)
        {
            oldentry.setUsername(oldentry.getUsername()!=null && !newentry.getUsername().equals("") ?newentry.getUsername(): oldentry.getUsername());
            oldentry.setPassword(oldentry.getPassword()!=null && !newentry.getPassword().equals("") ?newentry.getPassword(): oldentry.getPassword());
            userservice.adduserencoded(oldentry);
            return new ResponseEntity<>(oldentry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?>  delete(@RequestBody user newentry)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username= authentication.getName();;
        userrepository.deleteByUsername(username);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
