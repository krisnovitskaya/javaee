package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.CategoryRepr;

import java.util.List;

public interface CategoryService {
    void save(CategoryRepr category);

    void delete(Long id);

    CategoryRepr findById(long id);

    List<CategoryRepr> findAll();

    long count();
}
