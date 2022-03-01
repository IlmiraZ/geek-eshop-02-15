package ru.ilmira.controller.dto;

import javax.validation.constraints.NotBlank;

public class BrandDto {
    private Long id;

    @NotBlank
    private String name;

    public BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
