package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.core.enumerated.Roles;
import io.luankuhlmann.ecommerce.model.Role;
import io.luankuhlmann.ecommerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Set<Role> assignRole(Roles name) {
        Set<Role> roles = new HashSet<>();
        Role role = getByName(name);
        roles.add(role);

        return roles;
    }

    private Role getByName(Roles name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role não encontrado"));
    }
}
