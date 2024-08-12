package com.kitri.bark_meow_party_server.controller;

import com.kitri.bark_meow_party_server.domain.User;
import com.kitri.bark_meow_party_server.dto.AuthRequestDTO;
import com.kitri.bark_meow_party_server.dto.AuthResponseDTO;
import com.kitri.bark_meow_party_server.dto.ErrorResponseDTO;
import com.kitri.bark_meow_party_server.jwt.JwtUtil;
import com.kitri.bark_meow_party_server.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        doctorService.signup(user);
        return user;
    }

    @GetMapping("/check-doctorname")
    public ResponseEntity<Boolean> checkDoctorname(@RequestParam("doctorname") String doctorname) {
        boolean check = doctorService.checkDoctorname(doctorname);
        return ResponseEntity.ok(!check);
    }
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam("nickname") String nickname) {
        boolean check = doctorService.checkNickname(nickname);
        return ResponseEntity.ok(!check);
    }
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        try {
            // AuthenticationManager를 사용하여 인증 시도
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );
            // 인증된 사용자 정보를 UserDetails 객체로 가져오기
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
            // JWT 토큰 생성
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new AuthResponseDTO(jwt));
        } catch (Exception e) {
            // 인증 실패 시 응답 처리
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO("Incorrect username or password"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() throws Exception {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDoctor() {
        try {
            doctorService.deleteUser();
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("Error deleting user"));
        }
    }

}
