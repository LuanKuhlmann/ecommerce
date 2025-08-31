package io.luankuhlmann.ecommerce.service;

import io.luankuhlmann.ecommerce.core.enumerated.Roles;
import io.luankuhlmann.ecommerce.dto.request.UserRequest;
import io.luankuhlmann.ecommerce.dto.response.UserCreatedResponse;
import io.luankuhlmann.ecommerce.mapper.UserMapper;
import io.luankuhlmann.ecommerce.model.User;
import io.luankuhlmann.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RoleService roleService;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserCreatedResponse createUser(UserRequest userRequest) {
        String encodedPassword = passwordEncoder.encode(userRequest.password());
        User newUser = UserMapper.toEntity(userRequest, encodedPassword);
        newUser.setRoles(roleService.assignRole(Roles.USER));
        User savedUser = userRepository.save(newUser);
        return UserMapper.toUserCreatedResponse(savedUser);
    }

    public User finByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado para o email: " + email));
    }
}
