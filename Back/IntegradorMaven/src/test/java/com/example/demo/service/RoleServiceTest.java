package com.example.demo.service;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    @Test
    @DisplayName("Esta prueba valida la eliminacion de un role que no existe")
    public void delete_InvalidIdRoleTest() {
        Mockito.when(roleRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> roleService.deleteById(1),
                "No existe un role registrado con el id: 1");

        Mockito.verify(roleRepository, Mockito.times(1)).findById(1);
        Mockito.verify(roleRepository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de un role nulo")
    public void update_nullTest() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> roleService.updateRole(null),
                "El role no existe");

        Mockito.verify(roleRepository, Mockito.times(0)).findById(null);
    }

}


