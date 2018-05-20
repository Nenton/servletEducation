package ru.innopolis.stc9.susev.pojo;

public class Role {
    private int id;
    private String role;

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public Role(int roleId) {
        this.id = roleId;
    }

    public Role(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
