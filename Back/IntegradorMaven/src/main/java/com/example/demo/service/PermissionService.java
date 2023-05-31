package com.example.demo.service;

import com.example.demo.DTO.PermissionDTO;
import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService {
    private PermissionRepository  repository;


    @Autowired
    public PermissionService(PermissionRepository repository) {
        this.repository = repository;
    }

    public Permission findById(Integer id) {//Find a Manager by CC
        return repository.findById(id).get();
    }

    public Permission save(PermissionDTO permissionDTO) { //
        if (permissionDTO == null) {
            throw new ResourceNotFoundException("El permiso no puede ser nulo");
        }

      Permission permission = new Permission(
                permissionDTO.getName(),
                permissionDTO.getId()
        );
        return repository.save(permission);
    }

    public String deleteById(Integer id) {
        Optional<Permission> permissionOpt = this.repository.findById(id);

        if (!permissionOpt.isPresent()) {
            throw new ResourceNotFoundException("No existe un permiso registrado con el id: " + id);
        }
        repository.delete(permissionOpt.get());
        return "Se elimino exitosamente el permiso de id: " + id;
    }

    public void updatePermission(PermissionDTO permissionDTO) {
        if (permissionDTO != null) {
            Optional<Permission> permissionOpt = repository.findById(permissionDTO.getId());
            if (permissionOpt.isPresent()) {
              Permission permission = permissionOpt.get();
                permission.setName(permissionDTO.getName());
                repository.save(permission);
                return;
            }
        }
        throw new ResourceNotFoundException("El permiso no existe");
    }
}


