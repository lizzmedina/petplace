package com.example.demo.controller;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
    private RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping()
    public Role saveRole(@RequestBody RoleDTO roleDTO) {
        return service.save(roleDTO);
    }

    @GetMapping("/{id}")
    public Role findRole(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable("id") Integer id) { //localhost:8080/api/v1/manager
        return service.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<String> updateRole(@RequestBody RoleDTO roleDTO) {
        service.updateRole(roleDTO);
        return ResponseEntity.ok("Role Actualizado correctamente");

    }
}
