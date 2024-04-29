package com.river.socio.torcedor.projeto.socio.torcedor.river.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.Role;
import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.User;
import com.river.socio.torcedor.projeto.socio.torcedor.river.repository.RoleRepository;
import com.river.socio.torcedor.projeto.socio.torcedor.river.repository.UserRepository;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        System.out.println(roleAdmin);

        var userAdmin = userRepository.findByUsername("ana");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new User();
                    user.setUsername("ana");
                    user.setPassword(passwordEncoder.encode("1234"));
                    user.setRoles(roleAdmin);
                    userRepository.save(user);
                });
    }
}