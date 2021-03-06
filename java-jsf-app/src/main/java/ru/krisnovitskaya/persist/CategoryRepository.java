package ru.krisnovitskaya.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }

    public long count() {
        return em.createNamedQuery("countCategory", Long.class).getSingleResult();
    }

    @TransactionAttribute
    public void save(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @TransactionAttribute
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
