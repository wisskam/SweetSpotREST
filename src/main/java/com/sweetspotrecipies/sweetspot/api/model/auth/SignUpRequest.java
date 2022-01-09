package com.sweetspotrecipies.sweetspot.api.model.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private Set<String> role;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<String> getRole() {
        return role;
    }
    public void setRole(Set<String> role) {
        this.role = role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
