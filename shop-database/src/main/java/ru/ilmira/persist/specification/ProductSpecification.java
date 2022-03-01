package ru.ilmira.persist.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.ilmira.persist.model.Product;

import java.math.BigDecimal;

public final class ProductSpecification {
    public static Specification<Product> byCategory(long categoryId) {
        return (root, query, builder) -> builder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> byName(String pattern) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + pattern + "%");
    }

    public static Specification<Product> minPriceFilter(BigDecimal minPrice) {
        return (root, query, builder) -> builder.ge(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPriceFilter(BigDecimal maxPrice) {
        return (root, query, builder) -> builder.ge(root.get("price"), maxPrice);
    }
}
