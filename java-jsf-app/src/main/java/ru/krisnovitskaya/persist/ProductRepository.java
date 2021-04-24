package ru.krisnovitskaya.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public long count() {
        return em.createNamedQuery("countProduct", Long.class).getSingleResult();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public List<Product> findAllProductWithCategory() {
        return em.createNamedQuery("findAllProductWithCategory", Product.class)
                .getResultList();
    }

    public void delete(Long id) {
        em.createNamedQuery("deleteProductById").setParameter("id", id).executeUpdate();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProduct", Product.class).getResultList();
    }

    public List<Product> findByName(String name){
        return  em.createNamedQuery("findAllProductByName", Product.class).getResultList();
    }

    public List<Product> findAllByCategoryId(Long id){
        return em.createNamedQuery("findAllProductByCategory", Product.class).getResultList();
    }
}
