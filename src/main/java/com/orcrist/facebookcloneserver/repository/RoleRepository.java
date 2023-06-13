package com.orcrist.facebookcloneserver.repository;

import com.orcrist.facebookcloneserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String roleType);
}
