package com.example.demo.controller;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {

  @Mock RoleService roleService;

  @InjectMocks RoleController roleController;

  RoleDTO role = new RoleDTO(1, "USER_ROLE");

  @Test
  public void saveRoleTest() {
    roleController.saveRole(role);
    Mockito.verify(roleService).save(role);
  }

  @Test
  public void findRoleTest() {
    roleController.findRole(1);
    Mockito.verify(roleService).findById(1);
  }

  @Test
  public void deleteRoleTest() {
    roleController.deleteRole(1);
    Mockito.verify(roleService).deleteById(1);
  }

  @Test
  public void updateRoleTest() {
    roleController.updateRole(role);
    Mockito.verify(roleService).updateRole(role);
  }
}
