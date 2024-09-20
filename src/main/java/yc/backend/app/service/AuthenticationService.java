package yc.backend.app.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import yc.backend.app.dto.UserRegistrationRequest;
import yc.backend.app.model.Provider;
import yc.backend.app.model.User;
import yc.backend.app.repository.ProviderRepository;
import yc.backend.app.repository.UserRepository;
import yc.backend.app.utill.JwtUtil;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, ProviderRepository providerRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> registerUser(UserRegistrationRequest request) throws RuntimeException {
        if (userRepository.findByUsername(request.getUsername()) != null)
            throw new RuntimeException("Username already exists");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ofNullable(user);
    }

    public void registerProvider(UserRegistrationRequest request) throws RuntimeException {
        if (providerRepository.findByUsername(request.getUsername()) != null)
            throw new RuntimeException("Username already exists");
        Provider provider = new Provider();
        provider.setUsername(request.getUsername());
        provider.setPassword(passwordEncoder.encode(request.getPassword()));
        provider.setRole("ROLE_PROVIDER");
        providerRepository.save(provider);
    }

    public String login(UserRegistrationRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
