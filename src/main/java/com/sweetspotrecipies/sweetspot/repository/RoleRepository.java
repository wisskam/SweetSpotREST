package com.sweetspotrecipies.sweetspot.repository;

import com.sweetspotrecipies.sweetspot.model.ERole;
import com.sweetspotrecipies.sweetspot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}

