package com.example.demo.service;

import com.example.demo.DTO.UserAuthoritiesDTO;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SecurityService {

    private UserService userService;

    @Autowired
    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public UserAuthoritiesDTO getUserAuthorities(int userId) {
        User user = userService.findById(userId);
        List<Role> roles = user.getRoles();
        List<Permission> userPermissions = user.getPermissions();

        List<Permission> allPermissionsList = getPermissionsFromRoles(roles, userPermissions);
        Set<String> roleNames = getRoleNames(roles);
        Set<String> permissionNames = getPermissionNames(allPermissionsList);

        UserAuthoritiesDTO userAuthoritiesDTO = new UserAuthoritiesDTO(roleNames, permissionNames);
        return userAuthoritiesDTO;
    }

    private List<Permission> getPermissionsFromRoles(List<Role> roles, List<Permission> userPermissions) {
        Set<Permission> allPermissionsSet = new HashSet<>();

        for (int i = 0; i < roles.size(); i++) {
            Role roleInCurrentIndex = roles.get(i);
            Set<Permission> permissionsOfCurrentRole = roleInCurrentIndex.getPermissions();
            allPermissionsSet.addAll(permissionsOfCurrentRole);
        }
        allPermissionsSet.addAll(userPermissions);

        List<Permission> allPermissionsList = new ArrayList<>(allPermissionsSet);
        return allPermissionsList;
    }

    private Set<String> getPermissionNames(List<Permission> allPermissionsList) {
        Set<String> permissionNames = new HashSet<>();

        for (int i = 0; i < allPermissionsList.size(); i++) {
            Permission permissionInCurrentIndex = allPermissionsList.get(i);
            String permissionName = permissionInCurrentIndex.getName();
            permissionNames.add(permissionName);
        }
        return permissionNames;
    }

    private Set<String> getRoleNames(List<Role> roles) {
        Set<String> roleNames = new HashSet<>();

        for (int i = 0; i < roles.size(); i++) {
            Role roleInCurrentIndex = roles.get(i);
            String roleName = roleInCurrentIndex.getName();
            roleNames.add(roleName);
        }
        return roleNames;
    }
}
