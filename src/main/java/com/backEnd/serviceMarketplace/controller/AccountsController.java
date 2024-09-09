package com.backEnd.serviceMarketplace.controller;

import com.backEnd.serviceMarketplace.model.Account;
import com.backEnd.serviceMarketplace.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Management", description = "API for user management")
@CrossOrigin(value = "*")
public class AccountsController {


    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register New User", description = "add new user details into the database")
    public ResponseEntity<String> register(@RequestBody Account account) {
        try {
            accountService.register(account);
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Check user credentials", description = "check user details from the database")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        int account = accountService.login(email, password);
        if (account==1) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }
}
