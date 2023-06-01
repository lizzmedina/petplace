package com.example.demo.DTO;

public class PermissionDTO {
    private Integer id;
    private String name;

    public PermissionDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public PermissionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
