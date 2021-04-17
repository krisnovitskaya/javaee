package ru.krisnovitskaya.service.repr;

import java.io.Serializable;
import java.math.BigInteger;

public class CustomerRepr implements Serializable {
    private Long id;

    private String name;

    private BigInteger phone;

    private String address;

    public CustomerRepr(Long id, String name, BigInteger phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public CustomerRepr() {
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
