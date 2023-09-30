package az.sariyevtech.ecommerce.services.impl;

import az.sariyevtech.ecommerce.dto.authDto.TokenResponseDto;
import az.sariyevtech.ecommerce.dto.request.LoginRequest;
import az.sariyevtech.ecommerce.dto.request.RegisterRequest;
import az.sariyevtech.ecommerce.exception.CustomException;
import az.sariyevtech.ecommerce.services.StoreService;
import az.sariyevtech.ecommerce.util.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static az.sariyevtech.ecommerce.util.ErrorMessages.WRONG_USER_DETAIL;


@Service
public class AuthService {
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserService userService,
                       TokenGenerator tokenGenerator,
                       AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenGenerator = tokenGenerator;
        this.authenticationManager = authenticationManager;
    }

    public static String getLoggedInUsername() {
        return ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
    }

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            return TokenResponseDto.builder()
                    .accessToken(tokenGenerator.generateToken(auth))
                    .userDto(userService.getUser(loginRequest.getEmail()))
                    .build();
        } catch (Exception e) {
            throw new CustomException(WRONG_USER_DETAIL, " ");
        }
    }

    public TokenResponseDto register(RegisterRequest request) {
        userService.createUser(request);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return TokenResponseDto.builder()
                .accessToken(tokenGenerator.generateToken(auth))
                .userDto(userService.getUser(request.getEmail()))
                .build();
    }
}
