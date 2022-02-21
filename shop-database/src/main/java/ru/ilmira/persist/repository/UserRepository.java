package ru.ilmira.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilmira.persist.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
