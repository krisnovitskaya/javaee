package ru.krisnovitskaya.service;

import ru.krisnovitskaya.dtos.RoleRepr;
import ru.krisnovitskaya.persist.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @EJB
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleRepr> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(RoleRepr::new)
                .collect(Collectors.toList());
    }
}
