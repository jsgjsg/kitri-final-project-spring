package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.AuthRequestDTO;
import com.kitri.bark_meow_party_server.dto.AuthResponseDTO;
import com.kitri.bark_meow_party_server.dto.ErrorResponseDTO;
import com.kitri.bark_meow_party_server.jwt.JwtUtil;
import com.kitri.bark_meow_party_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        userService.signup(user);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDTO authRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new AuthResponseDTO(jwt));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("Incorrect username or password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() throws Exception {
        // SecurityContextHolder에서 인증 정보 제거
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logged out successfully");
    }
}
