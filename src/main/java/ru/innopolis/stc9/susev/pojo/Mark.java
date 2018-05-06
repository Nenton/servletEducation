package ru.innopolis.stc9.susev.pojo;

public class Mark {
    private int id;
    private int value;
    private String system;

    public Mark(int id, int value, String system) {
        this.id = id;
        this.value = value;
        this.system = system;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
