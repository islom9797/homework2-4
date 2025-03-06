package org.islom.dars241.controller;


import org.islom.dars241.payload.LoginDto;
import org.islom.dars241.security.JwtProvider;
import org.islom.dars241.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
   JwtProvider jwtProvider;

   @Autowired
    MyAuthService authService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public   HttpEntity<?> loginv() {
        return ResponseEntity.ok("Helllo worldddd");
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto dto) {
        System.out.println(dto.toString());
        /// 1qism
//        UserDetails userDetails = authService.loadUserByUsername(dto.getUsername());
//
//        System.out.println(userDetails.getPassword());
//        System.out.println(userDetails.getUsername());
//        boolean existUser = passwordEncoder.matches(dto.getPassword(), userDetails.getPassword());
//        System.out.println(existUser);
//        if(existUser){
//            String generateToken = jwtProvider.generateToken(dto.getUsername());
//            return ResponseEntity.ok(generateToken);
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login yoki parol Hato");
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()));
            System.out.println(dto);

            String token = jwtProvider.generateToken(dto.getUsername());
            return ResponseEntity.ok(token);

        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("username yoki parol hato");
        }


    }
}
