package com.example.pharmasn.auth;
import  com.example.pharmasn.user.entity.Role;
import com.example.pharmasn.config.security.JwtService;
import com.example.pharmasn.config.security.SecurityUser;
import com.example.pharmasn.user.entity.User;
import com.example.pharmasn.user.exceptions.EmailAlreadyUsedException;
import com.example.pharmasn.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException("Cet email est déjà utilisé.");
        }

        if (request.getNumeroLicence() != null && userRepository.existsByNumeroLicence(request.getNumeroLicence())) {
            throw new RuntimeException("Ce numéro de licence est déjà enregistré.");
        }

        User user = new User();
        user.setNom(request.getNom());
        user.setPrenom(request.getPrenom());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.PHARMACIEN);
        user.setNumeroLicence(request.getNumeroLicence());
        
        userRepository.save(user);
        
        String jwtToken = jwtService.generateToken(new SecurityUser(user));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        
        String jwtToken = jwtService.generateToken(new SecurityUser(user));
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
