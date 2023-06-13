package com.orcrist.facebookcloneserver.controller;

import com.orcrist.facebookcloneserver.dto.auth.LoginDto;
import com.orcrist.facebookcloneserver.dto.auth.LoginResponseDto;
import com.orcrist.facebookcloneserver.dto.auth.RegisterDto;
import com.orcrist.facebookcloneserver.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody @Valid RegisterDto dto) throws ParseException, MessagingException {
        return authService.register(dto);
    }

    @GetMapping("/activate/{token}")
    public ApiResponse activate(@PathVariable String token) {
        return authService.activate(token);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDto dto) throws ParseException {
        return authService.login(dto);
    }
}
