package com.example.demo.service;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RoleRepository;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

  @Mock RoleRepository roleRepository;

  @Mock PermissionService permissionService;

  @InjectMocks RoleService roleService;

  @Test
  @DisplayName("Esta prueba valida la eliminacion de un role que no existe")
  public void delete_InvalidIdRoleTest() {
    Mockito.when(roleRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

    Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> roleService.deleteById(1),
        "No existe un role registrado con el id: 1");

    Mockito.verify(roleRepository, Mockito.times(1)).findById(1);
    Mockito.verify(roleRepository, Mockito.times(0)).delete(Mockito.any());
  }

  @Test
  @DisplayName("Esta prueba valida la eliminacion de un role que si existe")
  public void delete_ValidIdRoleTest() {
    Role role = new Role("USER_ROLE", 1);
    Mockito.when(roleRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(role));

    String mensaje = roleService.deleteById(1);

    Assertions.assertEquals("Se elimino exitosamente el role de id: 1", mensaje);
    Mockito.verify(roleRepository, Mockito.times(1)).findById(1);
    Mockito.verify(roleRepository, Mockito.times(1)).delete(role);
  }

  @Test
  @DisplayName("Esta prueba valida la actualizacion de un role nulo")
  public void update_nullTest() {
    Assertions.assertThrows(
        ResourceNotFoundException.class, () -> roleService.updateRole(null), "El role no existe");

    Mockito.verify(roleRepository, Mockito.times(0)).findById(null);
  }

  @Test
  @DisplayName("Esta prueba valida la actualizacion de un role correctamente")
  public void update_successTest() {
    Role found = new Role("USER_ROLE", 1);
    RoleDTO roleDTO = new RoleDTO(1, "USER_ROLE_UPDATE");

    Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(found));
    Mockito.when(roleRepository.save(found)).thenReturn(found);
    RoleDTO actual = roleService.updateRole(roleDTO);

    Assertions.assertEquals("USER_ROLE_UPDATE", actual.getName());

    Mockito.verify(roleRepository).findById(1);
    Mockito.verify(roleRepository).save(found);
  }

  @Test
  public void save_successTest() {
    RoleDTO roleDTO = new RoleDTO(1, "USER_ROLE");
    Mockito.when(roleRepository.save(Mockito.any())).thenReturn(new Role("USER_ROLE", 1));

    Role actual = roleService.save(roleDTO);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("USER_ROLE", actual.getName());
  }

  @Test
  public void save_exceptionTest() {
    Assertions.assertThrows(ResourceNotFoundException.class, () -> roleService.save(null));
  }

  @Test
  public void findByIdTest() {
    Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(new Role("ADMIN_ROLE", 1)));
    roleService.findById(1);
    Mockito.verify(roleRepository).findById(1);
  }

  @Test
  public void findByNameTest() {
    Mockito.when(roleRepository.findByName("ADMIN_ROLE"))
        .thenReturn(Optional.of(new Role("ADMIN_ROLE", 1)));
    roleService.findByName("ADMIN_ROLE");
    Mockito.verify(roleRepository).findByName("ADMIN_ROLE");
  }

  @Test
  public void addPermissionToRoleTest() {
    Role role = new Role("USER_ROLE", 1);
    role.setPermissions(new HashSet<>());
    Permission permission = new Permission("CREATE", 1);

    Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
    Mockito.when(permissionService.findById(1)).thenReturn(permission);

    roleService.addPermissionToRole(1, 1);

    Mockito.verify(roleRepository).save(role);
  }

  @Test
  public void removePermissionToRoleTest() {
    Role role = new Role("USER_ROLE", 1);
    Permission permission = new Permission("CREATE", 1);
    Set<Permission> rolePermissions = new HashSet<>();
    rolePermissions.add(permission);
    role.setPermissions(rolePermissions);

    Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
    Mockito.when(permissionService.findById(1)).thenReturn(permission);

    roleService.removePermissionFromRole(1, 1);

    Mockito.verify(roleRepository).save(role);
  }

  @Test
  @DisplayName("Valida que no se elimine un permiso que no pertenezca al rol dado")
  public void removePermissionToRole_notValidTest() {
    Role role = new Role("USER_ROLE", 1);
    Permission permission = new Permission("CREATE", 1);
    role.setPermissions(Collections.emptySet());

    Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(role));
    Mockito.when(permissionService.findById(1)).thenReturn(permission);

    roleService.removePermissionFromRole(1, 1);

    Mockito.verify(roleRepository, Mockito.times(0)).save(role);
  }
}
