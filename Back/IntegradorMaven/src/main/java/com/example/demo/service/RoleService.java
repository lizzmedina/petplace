package com.example.demo.service;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoleService {

    private RoleRepository repository;


    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role save(RoleDTO roleDTO) { //
        if (roleDTO == null) {
            throw new ResourceNotFoundException("El role no puede ser nulo");
        }

        Role role = new Role(
                roleDTO.getName(),
                roleDTO.getId()
        );
        return repository.save(role);
    }

    public Role findById(Integer id) {//Find a Manager by CC
        return repository.findById(id).get();
    }

    public String deleteById(Integer id) {
        Optional<Role> roleOpt = this.repository.findById(id);

        if (!roleOpt.isPresent()) {
            throw new ResourceNotFoundException("No existe un role registrado con el id: " + id);
        }
        repository.delete(roleOpt.get());
        return "Se elimino exitosamente el role de id: " + id;
    }

    public void updateRole(RoleDTO roleDTO) {
        if (roleDTO != null) {
            Optional<Role> roleOpt = repository.findById(roleDTO.getId());
            if (roleOpt.isPresent()) {
                Role role = roleOpt.get();
                role.setName(roleDTO.getName());
                repository.save(role);
                return;
            }
        }
        throw new ResourceNotFoundException("El role no existe");
    }

}