package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.dtos.UserRepr;
import ru.krisnovitskaya.persist.Customer;
import ru.krisnovitskaya.persist.CustomerRepository;
import ru.krisnovitskaya.service.CustomerService;
import ru.krisnovitskaya.service.UserService;
import ru.krisnovitskaya.service.repr.CategoryRepr;
import ru.krisnovitskaya.service.repr.CustomerRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@SessionScoped
@Named
public class CustomerController implements Serializable {

    @EJB
    private CustomerService customerService;

    @EJB
    private UserService userService;

    private CustomerRepr customer;

    private List<CustomerRepr> customerList;

    private List<UserRepr> users;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.customerList = customerService.findAll();
        this.users = userService.getAllUsers();
    }

    public List<CustomerRepr> findAll() {
        return customerList;
    }

    public void deleteCustomer(Customer customer) {
        customerService.delete(customer.getId());
    }

    public String saveCustomer() {
        customerService.save(customer);
        return "/customer.xhtml?faces-redirect=true";
    }

    public String addCustomer() {
        this.customer = new CustomerRepr();
        return "/customer_form.xhtml?faces-redirect=true";
    }

    public String editCustomer(CustomerRepr customer) {
        this.customer = customer;
        return "/customer_form.xhtml?faces-redirect=true";
    }

    public CustomerRepr getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerRepr customer) {
        this.customer = customer;
    }
}