package com.example.demo.controller;

// ANDREA

import com.example.demo.entity.Manager;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    private ManagerService service;

    @Autowired

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    @GetMapping("/pruebaManager")//localhost:8080/api/manager/pruebaManager
    public String prueba(){
        return "conectando manager";
    }

    @PostMapping("/aManager")//localhost:8080/api/manager/aManager
    public Manager saveManager(@RequestBody Manager manager){
        return service.saveManager(manager);
    }

    @GetMapping("/aManager") //localhost:8080/api/manager/aManager
    public Manager findManager(Integer id){
        return service.findManager(id);
    }

    @DeleteMapping("/aManager")
    public String deleteManager(Integer id){ //localhost:8080/api/manager/aManager
        return service.deleteManager(id);
    }


}
