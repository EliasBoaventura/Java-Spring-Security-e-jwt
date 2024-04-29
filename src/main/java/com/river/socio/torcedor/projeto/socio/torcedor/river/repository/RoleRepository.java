package com.river.socio.torcedor.projeto.socio.torcedor.river.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.river.socio.torcedor.projeto.socio.torcedor.river.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
