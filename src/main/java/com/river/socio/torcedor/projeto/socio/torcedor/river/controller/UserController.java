package com.river.socio.torcedor.projeto.socio.torcedor.river.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.river.socio.torcedor.projeto.socio.torcedor.river.dto.CreateUserDto;
import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.Role;
import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.User;
import com.river.socio.torcedor.projeto.socio.torcedor.river.repository.RoleRepository;
import com.river.socio.torcedor.projeto.socio.torcedor.river.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping(value = "criar")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDto dto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        if (basicRole == null) { // Verifica se a role básica não foi encontrada
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Basic role not found");
        }

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(basicRole);

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "listar")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listUsers() {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}