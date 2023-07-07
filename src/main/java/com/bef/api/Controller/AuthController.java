/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bef.api.Controller;


import com.bef.api.Entity.Role;
import com.bef.api.Entity.UserEntity;
import com.bef.api.Repository.RolRepository;
import com.bef.api.Repository.UserRepository;
import com.bef.api.Security.CustomUserDetailsService;
import com.bef.api.Security.JwtGenerator;
import com.bef.api.dto.AuthResponseDTO;
import com.bef.api.dto.LoginDto;

import com.bef.api.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://foisbrian.online/","http://localhost:4200"})
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    private JwtGenerator jwtGenerator;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterDto registerDto) {
        // Verificar si el nombre de usuario ya existe
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            // El nombre de usuario ya está en uso, retorna una respuesta de error
            AuthResponseDTO errorResponse = new AuthResponseDTO("El nombre de usuario ya está en uso");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(registerDto.getUsername());

        String encryptedPassword = passwordEncoder.encode(registerDto.getPassword());
        newUser.setPassword(encryptedPassword);

        // Asignar roles al nuevo usuario
        List<Role> roles = new ArrayList<>();
        for (String roleName : registerDto.getRoles()) {
            Role role = rolRepository.findByNameIgnoreCase(roleName);
            if (role != null) {
                roles.add(role);
            }
        }
        newUser.setRoles(roles);

        UserEntity savedUser = userRepository.save(newUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        String token = jwtGenerator.generateToken(authentication);
        AuthResponseDTO responseDTO = new AuthResponseDTO(token);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
