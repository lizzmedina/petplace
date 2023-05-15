package com.example.demo.controller;

// ANDREA

import com.example.demo.entity.Manager;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private ManagerService service;

    @Autowired

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    @PostMapping()//localhost:8080/api/v1/manager
    public Manager saveManager(@RequestBody Manager manager){
        return service.saveManager(manager);
    }

    @GetMapping() //localhost:8080/api/v1/manager
    public Manager findManager(Integer id){
        return service.findManager(id);
    }

    @DeleteMapping()
    public String deleteManager(Integer id){ //localhost:8080/api/v1/manager
        return service.deleteManager(id);
    }


}
