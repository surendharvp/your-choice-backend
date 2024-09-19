package yc.backend.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import yc.backend.app.dto.LoginRequest;
import yc.backend.app.repository.ProviderRepository;
import yc.backend.app.repository.UserRepository;
import yc.backend.app.utill.JwtUtil;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void registerUser(LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }

    public void registerProvider(LoginRequest request) {
        if (providerRepository.findByUsername(request.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        Provider provider = new Provider();
        provider.setUsername(request.getUsername());
        provider.setPassword(passwordEncoder.encode(request.getPassword()));
        provider.setRole("ROLE_PROVIDER");
        providerRepository.save(provider);
    }

    public String login(LoginRequest loginRequest) throws Exception {
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
