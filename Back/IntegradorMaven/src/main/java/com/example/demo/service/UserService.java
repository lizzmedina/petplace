package com.example.demo.service;


import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) { //
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");// Save a user
        }
        return repository.save(user);
    }

    public User findById(Integer id) {//Find a Manager by CC
        return repository.findById(id).get();
    }

    public String deleteById(Integer id) {
        Optional<User> userOpt = this.repository.findById(id);

        if (!userOpt.isPresent()) {
            throw new ResourceNotFoundException("No existe un usuario registrado con el id: " + id);
        }
        repository.delete(userOpt.get());
        return "Se elimino exitosamente el usuario de id: " + id;
    }

    public List<UserDTO> getAllUsers() {
        List<User> usersFromDatabase = repository.findAll();
        List<UserDTO> listOfUserDtos = new ArrayList<>();

        for (int i = 0; i < usersFromDatabase.size(); i++) {
            User user = usersFromDatabase.get(i);
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getCellPhone(), user.getAddress(), user.getType());
            listOfUserDtos.add(userDTO);
        }

        return listOfUserDtos;
    }

    public void updateUser(UserDTO userDTO) {
        if (userDTO != null) {
            Optional<User> userOpt = repository.findById(userDTO.getId());
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setId(userDTO.getId());
                user.setName(userDTO.getName());
                user.setLastName(userDTO.getLastName());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                user.setCellPhone(userDTO.getCellPhone());
                user.setAddress(userDTO.getAddress());
                user.setType(userDTO.getType());
                repository.save(user);
                return;
            }
        }
        throw new ResourceNotFoundException("El usuario no existe");
    }

}
