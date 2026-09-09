package com.easybytes.easystore.controller;

import com.easybytes.easystore.dto.LoginRequestDto;
import com.easybytes.easystore.dto.LoginResponseDto;
import com.easybytes.easystore.dto.RegisterRequestDto;
import com.easybytes.easystore.dto.UserDto;
import com.easybytes.easystore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password())
            );
            var userDto = new UserDto();
            var loggedUser = (User) authentication.getPrincipal();
            userDto.setName(loggedUser.getUsername());
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), userDto, jwtToken)
            );
        } catch (BadCredentialsException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } catch (AuthenticationException e) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid RegisterRequestDto registerRequestDto) {
        inMemoryUserDetailsManager.createUser(new User(
                registerRequestDto.getEmail(),
                passwordEncoder.encode(registerRequestDto.getPassword()),
                List.of(new SimpleGrantedAuthority("USER"))
        ));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Registration successful");
    }

    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new LoginResponseDto(message, null, null));
    }
}
