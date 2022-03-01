package ru.ilmira.persist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ilmira.persist.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
