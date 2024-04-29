package com.river.socio.torcedor.projeto.socio.torcedor.river.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.river.socio.torcedor.projeto.socio.torcedor.river.dto.LoginRequest;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles;

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}