package com.example.demo.controller;

// ANDREA

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService service;

    @Autowired

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public User saveUser(@RequestBody User manager){
        return service.saveManager(manager);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable("id") Integer id){
        return service.findManager(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id")Integer id){ //localhost:8080/api/v1/manager
        return service.deleteManager(id);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers(){
        List<UserDTO> userDTO = service.getAllUsers();
        return userDTO;
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO){
        service.updateUser(userDTO);
        return ResponseEntity.ok("Usuario Actualizado correctamente");
    }


}
