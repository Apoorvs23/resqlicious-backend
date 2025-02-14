package com.apoorv.resqliciousbackend.service;

import com.apoorv.resqliciousbackend.dto.AuthRequest;
import com.apoorv.resqliciousbackend.dto.AuthResponse;
import com.apoorv.resqliciousbackend.dto.RegisterRequest;
import com.apoorv.resqliciousbackend.entity.User;
import com.apoorv.resqliciousbackend.exception.InvalidUsernamePasswordException;
import com.apoorv.resqliciousbackend.exception.UserAlreadyExistsException;
import com.apoorv.resqliciousbackend.jwt.JWTService;
import com.apoorv.resqliciousbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        final User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        try{
            userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new UserAlreadyExistsException(user.getEmail());
        }


        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw new InvalidUsernamePasswordException();
        }
        catch (Exception e){
            System.out.println(e);
            throw e;
        }


        final User user =  userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}