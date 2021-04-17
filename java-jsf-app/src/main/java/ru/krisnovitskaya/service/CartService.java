package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.ProductRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
    void add(ProductRepr productRepr);

    void remove(long id);

    List<ProductRepr> findAll();
}
