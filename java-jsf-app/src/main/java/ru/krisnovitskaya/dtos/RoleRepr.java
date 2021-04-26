package ru.krisnovitskaya.dtos;

import ru.krisnovitskaya.persist.Role;

import java.io.Serializable;
import java.util.Objects;

public class RoleRepr implements Serializable {

    private Long id;

    private String title;

    public RoleRepr(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public RoleRepr() {
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
    public RoleRepr(Role r) {
        this.id = r.getId();
        this.title = r.getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleRepr roleRepr = (RoleRepr) o;
        return Objects.equals(id, roleRepr.id) && Objects.equals(title, roleRepr.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
