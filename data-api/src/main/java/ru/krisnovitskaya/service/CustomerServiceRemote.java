package ru.krisnovitskaya.service;

import ru.krisnovitskaya.service.repr.CustomerRepr;

import java.util.List;

public interface CustomerServiceRemote {
    List<CustomerRepr> findAllRemote();
}
