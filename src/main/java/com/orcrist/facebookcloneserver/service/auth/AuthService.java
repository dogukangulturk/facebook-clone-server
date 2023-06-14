package com.orcrist.facebookcloneserver.service.auth;

import com.orcrist.facebookcloneserver.dto.auth.LoginDto;
import com.orcrist.facebookcloneserver.dto.auth.LoginResponseDto;
import com.orcrist.facebookcloneserver.dto.auth.RegisterDto;
import com.orcrist.facebookcloneserver.dto.user.UserMapper;
import com.orcrist.facebookcloneserver.exception.BadRequestException;
import com.orcrist.facebookcloneserver.model.Role;
import com.orcrist.facebookcloneserver.model.User;
import com.orcrist.facebookcloneserver.repository.RoleRepository;
import com.orcrist.facebookcloneserver.repository.UserRepository;
import com.orcrist.facebookcloneserver.security.jwt.JwtUtils;
import com.orcrist.facebookcloneserver.service.email.EmailService;
import com.orcrist.facebookcloneserver.util.ApiResponse;
import com.orcrist.facebookcloneserver.util.EmailDetails;
import com.orcrist.facebookcloneserver.util.GenerateUsername;
import com.orcrist.facebookcloneserver.util.RandomTokenGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.orcrist.facebookcloneserver.exception.NotFoundException;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    public ApiResponse register(RegisterDto dto) throws MessagingException, jakarta.mail.MessagingException {
        if (userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new BadRequestException(String.format("User with email %s already registered!", dto.getEmail()));
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setBirthday(LocalDate.parse(dto.getBirthday()));
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        addRoleToUser(user, "ROLE_USER");
        generateUsername.build(user, dto);
        String randomToken = randomTokenGenerator.setToken(dto.getEmail());
        setTokenFormUrl(randomToken);
        String emailResponse = emailService.sendEmailWithAttachment(setDetails(dto.getEmail(), randomToken));
        userRepository.save(user);
        return new ApiResponse(emailResponse);
    }

    public ApiResponse activate(String urlWithToken) {
        User user = userRepository.findByEmail(randomTokenGenerator.getEmail()).orElseThrow();
        if (randomTokenGenerator.verifyTokenTime(urlWithToken, getTokenFormUrl())) {
            if(user.getVerified()) {
                throw new BadRequestException("Account already activated");
            }
            user.setVerified(true);
            userRepository.save(user);
            return new ApiResponse("Account is activated!");
        }
        user.getRoles().removeAll(user.getRoles());
        userRepository.delete(user);
        throw new BadRequestException("Limited time (30 min) is expired! Register again and activate your account!");
    }

    public ResponseEntity<LoginResponseDto> login(LoginDto dto) throws ParseException {
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new NotFoundException("User", "email", dto.getEmail()));
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        if (!user.getVerified() && !getTokenFormUrl().isEmpty()) {
            if (!randomTokenGenerator.verifyTokenTime(randomToken, getTokenFormUrl())) {
                user.getRoles().removeAll(user.getRoles());
                userRepository.delete(user);
                throw new BadRequestException("Limited time (30 min) is expired! Register again and activate your account!");
            }
            throw new BadRequestException("Email address must verify first!");
        }
        String token = jwtUtils.generateToken(auth);
        return ResponseEntity.ok().body(new LoginResponseDto(UserMapper.INSTANCE.loginResponse(user), token));
    }

    public void addRoleToUser(User user, String roleType){
        Set<Role> roleArr = new HashSet<>();
        Role role = roleRepository.findByRole(roleType).orElse(null);
        roleArr.add(role);
        user.setRoles(roleArr);
    }

    public void setTokenFormUrl(String tokenFormUrl) {
        this.randomToken = tokenFormUrl;
    }

    public String getTokenFormUrl() {
        return randomToken;
    }

    private EmailDetails setDetails(String email, String token) {
        String url = String.format("%s/api/auth/activate/%s", clientUrl, token);
        String html = String.format("<!doctype html>\n" +
                "<html lang=en xmlns=http://www.w3.org/1999/xhtml xmlns:th=http://www.thymeleaf.org>\n" +
                "<head>\n" +
                "<meta charset=UTF-8>\n" +
                "<meta name=viewport content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "<meta http-equiv=X-UA-Compatible content=\"ie=edge\">\n" +
                "<title>Activation</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=margin-left:50px;margin-top:50px>\n" +
                "<div><b>Welcome!</b></div>\n" +
                "<br/>\n" +
                "<div>Please activate your account using the link within 30 minutes!</div>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<a href=%s style=\"width:200px;height:50px;background-color:blue;text-decoration:none;color:white;padding:13px 45px;border-radius:10px;fontSize:18\">\n" +
                "Activate\n" +
                "</a>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>", url);

        return new EmailDetails(email, "Account activation", html, "");
    }

}
