package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.ProductRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
    void save(ProductRepr product);

    void delete(Long id);

    ProductRepr findById(long id);

    List<ProductRepr> findAll();

    List<ProductRepr> findAllProductWithCategory();

    long count();
}
