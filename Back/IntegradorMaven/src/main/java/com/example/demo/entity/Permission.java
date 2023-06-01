package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;


@Entity
    @Table(name = "permission")
    public class Permission {

        @Column
        private String name;

        @Column
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

    @ManyToMany(mappedBy = "permissions")
    Set<Role> roles;

    @ManyToMany(mappedBy = "permissions")
    private Set<User> users;

        public Permission(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        public Permission() {

        }



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getIid() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



