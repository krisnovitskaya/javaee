package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.CategoryRepr;

import java.util.List;

public interface CategoryServiceRemote {
    List<CategoryRepr> findAllRemote();
}
