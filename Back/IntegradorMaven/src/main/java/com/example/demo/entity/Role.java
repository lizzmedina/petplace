package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "role")
public class Role {

  @Column
    private String name;

  @Column
  @Id
    private String id;

    public Role(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Role(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
