package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.persist.Category;
import ru.krisnovitskaya.persist.CategoryRepository;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {
    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categoryList = categoryRepository.findAll();
    }

    public List<Category> findAll() {
        return categoryList;
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category.getId());
    }

    public String saveCategory() {
        categoryRepository.save(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_form.xhtml?faces-redirect=true";
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
