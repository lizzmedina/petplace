package com.example.demo.controller;

import com.example.demo.DTO.RoleDTO;
import com.example.demo.DTO.UserAuthoritiesDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {

    @GetMapping("/user/{userId}/authorities")
    public UserAuthoritiesDTO getUserAuthorities(@PathVariable("userId") int userId) {
        RoleDTO role1 = new RoleDTO(1, "role uno");
        List<RoleDTO> roles = List.of(role1);

        List<String> permissions = List.of("permiso uno", "permiso dos");

        return new UserAuthoritiesDTO(roles, permissions);
    }
}
