package com.prajcompany.journal.service;

import com.prajcompany.journal.repository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.prajcompany.journal.entity.user;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    userrepository userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user userdeets= userrepo.findByUsername(username);
        if(userdeets!=null)
        {
            UserDetails userdatails= User.builder().username(userdeets.getUsername())
                    .password(userdeets.getPassword())
                    .roles(userdeets.getRoles().toArray(new String[0]))
                    .build();

            return userdatails;
        }
        throw new UsernameNotFoundException("username not found bro"+username);

    }
}
