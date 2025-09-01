package io.luankuhlmann.ecommerce.util;

import io.luankuhlmann.ecommerce.core.enumerated.Roles;
import io.luankuhlmann.ecommerce.model.Role;
import io.luankuhlmann.ecommerce.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RolesInit {
    private final RoleRepository roleRepository;

    @PostConstruct
    public void startRoles() {
        for (Roles roleName : Roles.values()) {
            boolean exists = roleRepository.existsByName(roleName);
            if (!exists) {
                Role role = new Role();
                role.setName(roleName);
                roleRepository.save(role);
            }
        }
    }
}
