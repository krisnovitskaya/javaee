package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.service.CategoryService;
import ru.krisnovitskaya.service.repr.CategoryRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {
    @EJB
    private CategoryService categoryService;

    private CategoryRepr category;

    private List<CategoryRepr> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categoryList = categoryService.findAll();
    }

    public List<CategoryRepr> findAll() {
        return categoryList;
    }

    public void deleteCategory(CategoryRepr category) {
        categoryService.delete(category.getId());
    }

    public String saveCategory() {
        categoryService.save(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new CategoryRepr();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String editCategory(CategoryRepr category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void setCategory(CategoryRepr category) {
        this.category = category;
    }

    public CategoryRepr getCategory() {
        return category;
    }
}
