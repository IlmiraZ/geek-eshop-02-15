package ru.ilmira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.ilmira.controller.dto.BrandDto;
import ru.ilmira.controller.dto.CategoryDto;
import ru.ilmira.controller.dto.ProductDto;
import ru.ilmira.persist.model.Picture;
import ru.ilmira.persist.model.Product;
import ru.ilmira.persist.repository.ProductRepository;
import ru.ilmira.persist.specification.ProductSpecification;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    @Override
    public Page<ProductDto> findAll(Optional<Long> categoryId, Optional<Long> brandId, Optional<String> namePattern,
                                    Integer page, Integer size, String sortField) {
        Specification<Product> spec = Specification.where(null);
        if (categoryId.isPresent() && categoryId.get() != -1) {
            spec = spec.and(ProductSpecification.byCategory(categoryId.get()));
        }
        if (brandId.isPresent() && brandId.get() != -1) {
            spec = spec.and(ProductSpecification.byBrand(brandId.get()));
        }
        if (namePattern.isPresent()) {
            spec = spec.and(ProductSpecification.byName(namePattern.get()));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                .map(this::toProductDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(this::toProductDto);
    }

    private ProductDto toProductDto(Product product) {
        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                new CategoryDto(product.getCategory().getId(), product.getCategory().getName()),
                new BrandDto(product.getBrand().getId(), product.getBrand().getName()),
                product.getPictures().stream().map(Picture::getId).collect(Collectors.toList()));
    }

}
