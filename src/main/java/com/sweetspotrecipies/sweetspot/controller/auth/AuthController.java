package com.sweetspotrecipies.sweetspot.controller.auth;

import com.sweetspotrecipies.sweetspot.api.model.auth.JwtResponse;
import com.sweetspotrecipies.sweetspot.api.model.auth.LoginRequest;
import com.sweetspotrecipies.sweetspot.api.model.auth.MessageResponse;
import com.sweetspotrecipies.sweetspot.api.model.auth.SignUpRequest;
import com.sweetspotrecipies.sweetspot.model.ERole;
import com.sweetspotrecipies.sweetspot.model.Role;
import com.sweetspotrecipies.sweetspot.model.User;
import com.sweetspotrecipies.sweetspot.repository.RoleRepository;
import com.sweetspotrecipies.sweetspot.repository.UserRepository;
import com.sweetspotrecipies.sweetspot.security.BookUserDetails;
import com.sweetspotrecipies.sweetspot.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${url.prefix.api.v1}/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest
                                                      loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        BookUserDetails userDetails = (BookUserDetails)
                authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getId(),
                        userDetails.getEmail(),
                        roles
                )
        );
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest
                                                  signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Wprowadzony adres email jest już zajęty..."));
        }
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is notfound."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole =
                                roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole =
                                roleRepository.findByName(ERole.ROLE_MODERATOR)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(
                new MessageResponse("Rejestracja Użytkownika powiodła się.")
        );
    }
}
