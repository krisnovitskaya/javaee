package ru.krisnovitskaya.controller;


import ru.krisnovitskaya.service.CartService;
import ru.krisnovitskaya.service.repr.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    public CartService cartService;

    private List<ProductRepr> cartList;

    public void preloadData(ComponentSystemEvent componentSystemEvent){
        this.cartList = cartService.findAll();
    }

    public void add(ProductRepr productRepr){
        cartService.add(productRepr);
    }

    public void remove(long id){
        cartService.remove(id);
    }

    public List<ProductRepr> findAll(){
        return cartService.findAll();
    }
}
