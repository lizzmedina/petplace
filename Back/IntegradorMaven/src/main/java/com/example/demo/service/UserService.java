package com.example.demo.service;

//ANDREA

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    //ATRIBUTES
    private UserRepository repository;

    @Autowired

    //CONTROLLER
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    //METHODS
    public User saveManager(User manager){ // Save a manager
        return repository.save(manager);
    }

    public User findManager(Integer id){//Find a Manager by CC
        return repository.findById(id).get();
    }

    public String deleteManager(Integer id){
        Optional<User> manager = this.repository.findById(id);

        if(!manager.isPresent()){
            throw new RuntimeException("El administrador no existe");
        }
        repository.delete(manager.get());
        return "El Administrador fue eliminado ";

    }

    public List<UserDTO> getAllUsers(){
        List<User> usersFromDatabase = repository.findAll();
        List<UserDTO> listOfUserDtos = new ArrayList<>();

        for(int i= 0; i < usersFromDatabase.size(); i++){
            User manager = usersFromDatabase.get(i);
            UserDTO userDTO = new UserDTO(manager.getId(), manager.getName(), manager.getLastName(), manager.getEmail(), manager.getPassword(), manager.getCellPhone(), manager.getAddress(), manager.getType());
            listOfUserDtos.add(userDTO);
        }

        return listOfUserDtos;
    }


}
