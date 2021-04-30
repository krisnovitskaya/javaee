package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.persist.Category;
import ru.krisnovitskaya.persist.CategoryRepository;
import ru.krisnovitskaya.persist.Product;
import ru.krisnovitskaya.persist.ProductRepository;
import ru.krisnovitskaya.service.CategoryService;
import ru.krisnovitskaya.service.ProductService;
import ru.krisnovitskaya.service.repr.CategoryRepr;
import ru.krisnovitskaya.service.repr.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    private ProductRepr product;

    private List<ProductRepr> productList;

    private List<CategoryRepr> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent){
        this.productList = productService.findAllProductWithCategory();
        this.categories = categoryService.findAll();
    }

    public List<ProductRepr> findAll() {
        return productList;
    }

    public String editProduct(ProductRepr product) {
        this.product = product;
        return "/manager/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        productService.save(product);
        return "/manager/product.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        this.product = new ProductRepr();
        return "/manager/product_form.xhtml?faces-redirect=true";
    }

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public List<CategoryRepr> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryRepr> categories) {
        this.categories = categories;
    }
}
