package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.ProductRepr;

import java.util.List;

public interface ProductServiceRemote {
    List<ProductRepr> findAllRemote();
}
