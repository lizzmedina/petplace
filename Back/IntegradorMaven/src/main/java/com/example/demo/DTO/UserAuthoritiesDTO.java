package com.example.demo.DTO;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;

import java.util.List;
import java.util.Set;

public class UserAuthoritiesDTO {

    private Set<String> roles;
    private Set<String> permissions;

    public UserAuthoritiesDTO(Set<String> roles, Set<String> permissions) {
        this.roles = roles;
        this.permissions = permissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }
}
