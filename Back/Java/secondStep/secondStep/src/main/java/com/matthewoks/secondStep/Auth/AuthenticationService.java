package com.matthewoks.secondStep.Auth;

import com.matthewoks.secondStep.configs.JwtService;
import com.matthewoks.secondStep.models.RoleType;
import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(RoleType.USER)
                .createdAt(new Date())
                .isActive("Y")
                .build();
        repository.save(user);

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build() ;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){

        Optional<User> gg = repository.findByUsername(request.getUsername());
        System.out.println("Password inviata: " + request.getPassword());
        System.out.println("Password salvata (hash): " + gg.get().getPassword());
        System.out.println("Matches? " + passwordEncoder.matches(request.getPassword(), gg.get().getPassword()));
        System.out.println("Authorities: " + gg.get().getAuthorities());

        System.out.println("Password inviata: " + request.getUsername());
        System.out.println("Password salvata (hash): " + gg.get().getUsername());
        System.out.println("Matches? " + passwordEncoder.matches(request.getUsername(), gg.get().getUsername()));
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
//                    request.getPassword(),
//                    gg.get().getAuthorities()
                    )
        );

        var user = repository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));

//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build() ;
    }

}
