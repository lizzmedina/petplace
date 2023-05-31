package com.example.demo.controller;

import com.example.demo.DTO.PermissionDTO;
import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.service.PermissionService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {
    private PermissionService service;

    @Autowired
    public PermissionController(PermissionService service) {
        this.service = service;
    }

    @PostMapping()
    public Permission savePermission(@RequestBody PermissionDTO permissionDTO) {
        return service.save(permissionDTO);
    }

    @GetMapping("/{id}")
    public Permission findRole(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deletePermission(@PathVariable("id") Integer id) { //localhost:8080/api/v1/manager
        return service.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<String> updatePermission(@RequestBody PermissionDTO permissionDTO) {
        service.updatePermission(permissionDTO);
        return ResponseEntity.ok("Permiso Actualizado correctamente");

    }
}


