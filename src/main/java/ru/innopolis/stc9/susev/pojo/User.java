package ru.innopolis.stc9.susev.pojo;

import java.sql.Timestamp;

public class User {
    private int id;
    private String login;
    private String passwordHash;
    private Role role;
    private String fullName;

    public User(int id, String login, String passwordHash, Role role, String fullName) {
        this.id = id;
        this.login = login;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
    }

    public User(String name, String login, String password, int roleId) {
        this.login = login;
        this.passwordHash = password;
        this.role = new Role(roleId);
        this.fullName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
