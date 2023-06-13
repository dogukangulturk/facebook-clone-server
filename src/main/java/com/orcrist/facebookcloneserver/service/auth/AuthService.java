package com.orcrist.facebookcloneserver.service.auth;

import com.orcrist.facebookcloneserver.dto.auth.RegisterDto;
import com.orcrist.facebookcloneserver.repository.RoleRepository;
import com.orcrist.facebookcloneserver.repository.UserRepository;
import com.orcrist.facebookcloneserver.security.jwt.JwtUtils;
import com.orcrist.facebookcloneserver.service.email.EmailService;
import com.orcrist.facebookcloneserver.util.ApiResponse;
import com.orcrist.facebookcloneserver.util.GenerateUsername;
import com.orcrist.facebookcloneserver.util.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Value("${client.base_url}")
    private String clientUrl;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenerateUsername generateUsername;
    private final RandomTokenGenerator randomTokenGenerator;
    private final EmailService emailService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private String randomToken;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       GenerateUsername generateUsername,
                       RandomTokenGenerator randomTokenGenerator,
                       EmailService emailService,
                       JwtUtils jwtUtils,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.generateUsername = generateUsername;
        this.randomTokenGenerator = randomTokenGenerator;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }
}
