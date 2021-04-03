package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.persist.Category;
import ru.krisnovitskaya.persist.CategoryRepository;
import ru.krisnovitskaya.persist.Customer;
import ru.krisnovitskaya.persist.CustomerRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class CustomerController implements Serializable {
    @Inject
    private CustomerRepository customerRepository;

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCategory(Customer customer) {
        this.customer = customer;
    }
    public List<Customer> findAll(){ return customerRepository.findAll();}

    public void deleteCustomer(Customer customer) {customerRepository.delete(customer.getId());}

    public String saveCustomer(){
        customerRepository.save(customer);
        return "/customer.xhtml?faces-redirect=true";
    }

    public String addCustomer(){
        this.customer = new Customer();
        return "/customer_form.xhtml?faces-redirect=true";
    }
}