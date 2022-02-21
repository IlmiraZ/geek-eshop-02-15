package ru.ilmira.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilmira.persist.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
