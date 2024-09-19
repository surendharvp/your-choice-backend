package yc.backend.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Register User
    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        authenticationService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }

    // Register Service Provider
    @PostMapping("/register/provider")
    public ResponseEntity<?> registerProvider(@RequestBody LoginRequest request) {
        authenticationService.registerProvider(request);
        return ResponseEntity.ok("Service Provider registered successfully");
    }

    // Login User/Provider
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = authenticationService.login(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
