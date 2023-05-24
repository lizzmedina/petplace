package com.example.demo.service;


import com.example.demo.DTO.ManagerDTO;
import com.example.demo.entity.Manager;
import com.example.demo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private ManagerRepository repository;

    @Autowired

    public ManagerService(ManagerRepository repository) {
        this.repository = repository;
    }

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

    public List<ManagerDTO> getAllManagers(){
        List<Manager> managersFromDatabase = repository.findAll();
        List<ManagerDTO> listOfManagerDtos = new ArrayList<>();

        for(int i= 0; i < managersFromDatabase.size(); i++){
            Manager manager = managersFromDatabase.get(i);
            ManagerDTO managerDTO = new ManagerDTO(manager.getId(), manager.getName(), manager.getLastName(), manager.getEmail(), manager.getPassword(), manager.getCellPhone(), manager.getAddress(), manager.getType());
            listOfManagerDtos.add(managerDTO);
        }

        return listOfManagerDtos;
    }


}
