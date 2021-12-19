package com.sweetspotrecipies.sweetspot.security;

import com.sweetspotrecipies.sweetspot.model.User;
import com.sweetspotrecipies.sweetspot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public BookUserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono uzytkownika o nazwie: " + username));
        return BookUserDetails.build(user);
    }
}