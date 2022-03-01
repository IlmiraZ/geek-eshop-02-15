package ru.ilmira.persist.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ilmira.persist.model.User;

public class UserSpecification {
    public static Specification<User> nameLike(String pattern) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("login")), "%" + pattern + "%");
    }
}
