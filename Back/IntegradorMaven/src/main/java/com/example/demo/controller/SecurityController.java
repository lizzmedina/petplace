package com.example.demo.controller;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.DTO.UserAuthoritiesDTO;
import com.example.demo.service.RoleService;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {
    public UserService userService;
    public RoleService roleService;
    public SecurityService securityService;

    @Autowired
    public SecurityController(UserService userService, RoleService roleService, SecurityService securityService) {
        this.userService = userService;
        this.roleService = roleService;
        this.securityService = securityService;
    }

    @GetMapping("/user/{userId}/authorities")
    public UserAuthoritiesDTO getUserAuthorities(@PathVariable("userId") int userId) {
     return securityService.getUserAuthorities(userId);

    }

    @PutMapping("/link/user/{userId}/role/{roleId}")
    public void addRoleToUser(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
        userService.addRoleToUser(userId, roleId);
    }

    @DeleteMapping("/unlink/user/{userId}/role{roleId}")
    public void removeRoleFromUser(@PathVariable("userId") Integer userId, @PathVariable("roleId") Integer roleId) {
        userService.removeRoleFromUser(userId, roleId);
    }

    @PutMapping("/link/permission/{permissionId}/role/{roleId}")
    public void addPermissionToRole(@PathVariable("permissionId") Integer permissionId, @PathVariable("roleId") Integer roleId) {
        roleService.addPermissionToRole(roleId, permissionId);
    }

    @DeleteMapping("/unlink/permission/{permissionId}/role/{roleId}")
    public void removePermissionFromRole(@PathVariable("permissionId") Integer permissionId, @PathVariable("roleId") Integer roleId) {
    roleService.removePermissionFromRole(permissionId, roleId);
    }

    @PutMapping("/link/permission/{permissionId}/user/{userId}")
    public void addPermissionToUser(@PathVariable("permissionId") Integer permissionId, @PathVariable("userId") Integer userId){
        userService.addPermissionToUser(userId, permissionId);
    }

    @DeleteMapping("/unlink/permission/{permissionId}/user/{userId}")
    public void removePermissionFromUser(@PathVariable("permissionId")Integer permissionId, @PathVariable("userId") Integer userId){
        userService.removePermissionFromUSer(userId, permissionId);
    }
}
