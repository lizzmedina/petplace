package com.example.demo.controller;

import com.example.demo.service.RoleService;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SecurityControllerTest {

  @Mock UserService userService;
  @Mock RoleService roleService;
  @Mock SecurityService securityService;
  @InjectMocks SecurityController securityController;

  @Test
  public void getUserAuthoritiesTest() {
    securityController.getUserAuthorities(1);
    Mockito.verify(securityService).getUserAuthorities(1);
  }

  @Test
  public void addRoleToUserTest() {
    securityController.addRoleToUser(1, 1);
    Mockito.verify(userService).addRoleToUser(1, 1);
  }

  @Test
  public void removeRoleFromUserTest() {
    securityController.removeRoleFromUser(1, 1);
    Mockito.verify(userService).removeRoleFromUser(1, 1);
  }

  @Test
  public void addPermissionToRoleTest() {
    securityController.addPermissionToRole(1, 1);
    Mockito.verify(roleService).addPermissionToRole(1, 1);
  }

  @Test
  public void removePermissionFromRoleTest() {
    securityController.removePermissionFromRole(1, 1);
    Mockito.verify(roleService).removePermissionFromRole(1, 1);
  }

  @Test
  public void addPermissionToUserTest() {
    securityController.addPermissionToUser(1, 1);
    Mockito.verify(userService).addPermissionToUser(1, 1);
  }

  @Test
  public void removePermissionFromUserTest() {
    securityController.removePermissionFromUser(1, 1);
    Mockito.verify(userService).removePermissionFromUser(1, 1);
  }
}
