package ru.krisnovitskaya.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id"),
        @NamedQuery(name = "findAllCategory", query = "from Category c"),
        @NamedQuery(name = "countCategory", query = "select count(c) from Category c")
})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
