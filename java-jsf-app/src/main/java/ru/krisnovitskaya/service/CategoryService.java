package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.CategoryRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {
    void save(CategoryRepr category);

    void delete(Long id);

    CategoryRepr findById(long id);

    List<CategoryRepr> findAll();

    long count();
}
