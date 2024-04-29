package com.river.socio.torcedor.projeto.socio.torcedor.river.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
