package ru.krisnovitskaya.persist;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id = :id"),
        @NamedQuery(name = "findAllCustomer", query = "from Customer c"),
        @NamedQuery(name = "countCustomer", query = "select count(c) from Customer c")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private BigInteger phone;

    @Column
    private String address;

    public Customer() {
    }

    public Customer(Long id, String name, BigInteger phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
