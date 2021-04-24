package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.CustomerRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CustomerService {
    void save(CustomerRepr customer);

    void delete(Long id);

    CustomerRepr findById(long id);

    List<CustomerRepr> findAll();

    long count();
}
