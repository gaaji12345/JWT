package com.example.JWTGaaji.api;/*  gaajiCode
    99
    18/09/2024
    */

import com.example.JWTGaaji.dto.AuthResponceDTO;
import com.example.JWTGaaji.dto.LoginDto;
import com.example.JWTGaaji.dto.RegisterDto;
import com.example.JWTGaaji.entity.Roles;
import com.example.JWTGaaji.entity.UserE;
import com.example.JWTGaaji.repo.RoleRepo;
import com.example.JWTGaaji.repo.UserRepo;
import com.example.JWTGaaji.security.JWTGenarator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepository;
    private RoleRepo roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenarator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepository,
                          RoleRepo roleRepository, PasswordEncoder passwordEncoder,JWTGenarator jwtGenarator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator=jwtGenarator;

    }


    @PostMapping("login")
    public ResponseEntity<AuthResponceDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponceDTO(token), HttpStatus.OK);
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserE user = new UserE();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

        Roles roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
    }
}
