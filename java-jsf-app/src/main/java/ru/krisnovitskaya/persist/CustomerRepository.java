package ru.krisnovitskaya.persist;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerRepository {
    private final Map<Long, Customer> customerMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public void save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(identity.incrementAndGet());
        }
        customerMap.put(customer.getId(), customer);
    }

    @PostConstruct
    private void init(){
        save(new Customer(null, "Customer name 1", new BigInteger("1234567891"), "Address 1"));
        save(new Customer(null, "Customer name 2", new BigInteger("1234567892"), "Address 2"));
        save(new Customer(null, "Customer name 3", new BigInteger("1234567893"), "Address 3"));
        save(new Customer(null, "Customer name 4", new BigInteger("1234567894"), "Address 4"));
    }

    public void delete(Long id) {
        customerMap.remove(id);
    }

    public Customer findById(Long id) {
        return customerMap.get(id);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }
}
