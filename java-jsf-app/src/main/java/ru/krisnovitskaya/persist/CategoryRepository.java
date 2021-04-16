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
import java.util.List;


@ApplicationScoped
@Named
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() {
        if(count() == 0){
            try{
                ut.begin();
                save(new Category(null, "Category title 1"));
                save(new Category(null, "Category title 2"));
                save(new Category(null, "Category title 3"));
                save(new Category(null, "Category title 4"));
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
        return em.createNamedQuery("countCategory", Long.class).getSingleResult();
    }

    @Transactional
    public void save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void delete(Long id) {
        em.createNamedQuery("deleteCategoryById").setParameter("id", id).executeUpdate();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategory", Category.class).getResultList();
    }
}
