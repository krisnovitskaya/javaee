package ru.krisnovitskaya.service;

import ru.krisnovitskaya.persist.Customer;
import ru.krisnovitskaya.persist.CustomerRepository;
import ru.krisnovitskaya.service.repr.CustomerRepr;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CustomerServiceImpl implements CustomerService, CustomerServiceRemote{

    @EJB
    private CustomerRepository customerRepository;

    @Override
    public List<CustomerRepr> findAllRemote() {
        return findAll();
    }

    @Override
    @TransactionAttribute
    public void save(CustomerRepr customer) {
        customerRepository.save(new Customer(customer.getId(), customer.getName(), customer.getPhone(), customer.getAddress()));
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public CustomerRepr findById(long id) {
        return createCustomerRepr(customerRepository.findById(id));
    }

    @Override
    public List<CustomerRepr> findAll() {
        return customerRepository.findAll().stream().map(CustomerServiceImpl::createCustomerRepr).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    private static CustomerRepr createCustomerRepr(Customer customer){
        return new CustomerRepr(customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getUser().getId(),
                customer.getUser().getLogin());
    }
}
