package com.sweetspotrecipies.sweetspot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("${url.prefix.api.v1}/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Treść dostępna publicznie";
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Treść dostępna dla Użytkownika w dowolnej roli.";
    }
    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public String moderatorAccess() {
        return "Treść dostępna dla Moderatora oraz Administratora.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(@RequestHeader(name="Authorization") String token) {
        return "Treść dostępna dla Administratora." + token;
    }
}