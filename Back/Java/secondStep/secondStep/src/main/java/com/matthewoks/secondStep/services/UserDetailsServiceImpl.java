package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return UserDetailsImpl.build(user.get());

        } else {
            throw new UsernameNotFoundException("Utente " +username+ " non trovato.");
        }
    }
}
