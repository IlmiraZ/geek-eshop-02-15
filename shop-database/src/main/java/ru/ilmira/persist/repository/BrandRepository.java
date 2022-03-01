package ru.ilmira.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilmira.persist.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
