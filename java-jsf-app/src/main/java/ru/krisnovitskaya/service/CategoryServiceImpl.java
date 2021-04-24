package ru.krisnovitskaya.service;

import ru.krisnovitskaya.persist.Category;
import ru.krisnovitskaya.persist.CategoryRepository;
import ru.krisnovitskaya.service.repr.CategoryRepr;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRemote{

    @EJB
    private CategoryRepository categoryRepository;


    @Override
    public List<CategoryRepr> findAllRemote() {
        return findAll();
    }

    @Override
    @TransactionAttribute
    public void save(CategoryRepr category) {
        categoryRepository.save(new Category(category.getId(), category.getTitle()));
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public CategoryRepr findById(long id) {
        return createCategoryRepr(categoryRepository.findById(id));
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream().map(CategoryServiceImpl::createCategoryRepr).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }

    private static CategoryRepr createCategoryRepr(Category category){
        return  new CategoryRepr(category.getId(), category.getTitle());
    }
}
