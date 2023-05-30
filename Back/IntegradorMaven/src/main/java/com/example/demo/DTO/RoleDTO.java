package com.example.demo.DTO;

public class RoleDTO {

    private Integer id;
    private String name;

    public RoleDTO(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public RoleDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
