package ru.krisnovitskaya.controller;

import ru.krisnovitskaya.dtos.RoleRepr;
import ru.krisnovitskaya.dtos.UserRepr;
import ru.krisnovitskaya.service.RoleService;
import ru.krisnovitskaya.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {
    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    @Inject
    private HttpSession session;

    private UserRepr user;

    private List<RoleRepr> roles;

    private List<UserRepr> users;

    public void preLoad() {
        this.roles = roleService.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserRepr getUser() {
        return user;
    }

    public void setUser(UserRepr user) {
        this.user = user;
    }

    public List<UserRepr> getAllUsers() {
        return users;
    }

    @RolesAllowed({"ADMIN"})
    public String editUser(UserRepr user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    @RolesAllowed({"ADMIN"})
    public void deleteUser(UserRepr user) {
        userService.delete(user.getId());
    }

    public String createUser() {
        this.user = new UserRepr();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    @RolesAllowed({"ADMIN"})
    public String saveUser() {
        userService.saveOrUpdate(this.user);
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public List<RoleRepr> getAllRoles() {
        return roles;
    }

    public String logout() {
        session.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
