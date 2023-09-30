package az.sariyevtech.ecommerce.controller.authController;

import az.sariyevtech.ecommerce.dto.authDto.AccessToken;
import az.sariyevtech.ecommerce.dto.authDto.TokenResponseDto;
import az.sariyevtech.ecommerce.dto.request.LoginRequest;
import az.sariyevtech.ecommerce.dto.request.RegisterRequest;
import az.sariyevtech.ecommerce.model.user.User;
import az.sariyevtech.ecommerce.services.impl.AuthService;
import az.sariyevtech.ecommerce.services.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/user")
    public ResponseEntity<User> getValidate(@RequestBody AccessToken tokenResponse) {
        return ResponseEntity.ok(userService.findUserByEmail(tokenResponse));
    }
}