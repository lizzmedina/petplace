package com.example.demo.DTO;

import java.util.List;

public class UserAuthoritiesDTO {

    private List<RoleDTO> roles;
    private List<String> permissions;

    public UserAuthoritiesDTO(List<RoleDTO> roles, List<String> permissions) {
        this.roles = roles;
        this.permissions = permissions;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}
