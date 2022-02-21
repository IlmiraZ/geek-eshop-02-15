package ru.ilmira.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilmira.persist.model.Cart;


public interface CartRepository extends JpaRepository<Cart, Long> {
}
