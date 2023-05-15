package com.example.demo.service;

//ANDREA

import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService {
    //ATRIBUTES
    private ManagerRepository repository;

    @Autowired

    //CONTROLLER
    public ManagerService(ManagerRepository repository) {
        this.repository = repository;
    }

    //METHODS
    public Manager saveManager(Manager manager){ // Save a manager
        return repository.save(manager);
    }

    public Manager findManager(Integer id){//Find a Manager by CC
        return repository.findById(id).get();
    }

    public String deleteManager(Integer id){ // Delete a Manager by CC
        Optional<Manager> manager = this.repository.findById(id); //

        if(!manager.isPresent()){
            throw new RuntimeException("El administrador no existe");
        }
        repository.delete(manager.get());
        return "El Administrador fue eliminado ";

    }


}
