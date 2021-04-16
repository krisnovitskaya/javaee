package ru.krisnovitskaya.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigInteger;
import java.util.List;

@ApplicationScoped
@Named
public class CustomerRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() {
        if(count() == 0){
            try{
                ut.begin();
                save(new Customer(null, "Customer name 1", new BigInteger("1234567891"), "Address 1"));
                save(new Customer(null, "Customer name 2", new BigInteger("1234567892"), "Address 2"));
                save(new Customer(null, "Customer name 3", new BigInteger("1234567893"), "Address 3"));
                save(new Customer(null, "Customer name 4", new BigInteger("1234567894"), "Address 4"));
                ut.commit();
            }catch (Exception e){
                try {
                    ut.rollback();
                }catch (SystemException se){
                    throw new RuntimeException();
                }
                throw new RuntimeException();
            }
        }
    }

    private long count() {
        return em.createNamedQuery("countCustomer", Long.class).getSingleResult();
    }

    @Transactional
    public void save(Customer customer) {
        if (customer.getId() == null) {
            em.persist(customer);
        }
        em.merge(customer);
    }

    @Transactional
    public void delete(Long id) {
        em.createNamedQuery("deleteCustomerById").setParameter("id", id).executeUpdate();
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        return em.createNamedQuery("findAllCustomer", Customer.class).getResultList();
    }
}
