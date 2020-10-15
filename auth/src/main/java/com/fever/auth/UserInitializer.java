package com.fever.auth;

import com.fever.auth.entity.User;
import com.fever.auth.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserInitializer implements ApplicationRunner {

    @Autowired
    UserDao userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        PasswordEncoder passwordEncoder;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        List<GrantedAuthority> authoritylist = new ArrayList<>();
        authoritylist.add(new SimpleGrantedAuthority("ADMIN"));

        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setAuthorities(authoritylist);
        user.setDate(new Date());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userDao.save(user);
    }

}
