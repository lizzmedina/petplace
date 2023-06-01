package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cellPhone")
    private String cellPhone;

    @Column(name = " address")
    private String address;

    @Column(name = "type")
    private String type;


    @Column(name = "validation")
    private boolean validation;


    @OneToMany(mappedBy = "user")
    List<Booking> bookings;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;


    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void removePermission(Permission permission) {
        permissions.remove(permission);
    }

    public
        User(Integer id, String name, String lastName, String email, String password, String cellPhone, String address, String type,
        boolean validation){
            this.id = id;
            this.name = name;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.cellPhone = cellPhone;
            this.address = address;
            this.type = type;
            this.validation = validation;
        }

    public User() {
        }

        public Integer getId () {
            return id;
        }

        public void setId (Integer id){
            this.id = id;
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getLastName () {
            return lastName;
        }

        public void setLastName (String lastName){
            this.lastName = lastName;
        }

        public String getEmail () {
            return email;
        }

        public void setEmail (String email){
            this.email = email;
        }

        public String getPassword () {
            return password;
        }

        public void setPassword (String password){
            this.password = password;
        }

        public String getCellPhone () {
            return cellPhone;
        }

        public void setCellPhone (String cellPhone){
            this.cellPhone = cellPhone;
        }

        public String getAddress () {
            return address;
        }

        public void setAddress (String address){
            this.address = address;
        }

        public String getType () {
            return type;
        }

        public void setType (String type){
            this.type = type;
        }

        public boolean isValidation () {
            return validation;
        }

        public boolean setValidation ( boolean validation){
            return this.validation = validation;
        }

        public List<Role> getRoles () {
            return roles;
        }

        public void setRoles (List < Role > roles) {
            this.roles = roles;
        }

        public List<Permission> getPermissions () {
            return permissions;
        }

        public void setPermissions (List < Permission > permissions) {
            this.permissions = permissions;
        }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
