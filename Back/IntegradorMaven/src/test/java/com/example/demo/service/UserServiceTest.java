package com.example.demo.service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("Esta prueba valida la creacion de un usuario nulo")
    public void save_nullTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.save(null), "El usuario no puede ser nulo");

        Mockito.verify(repository, Mockito.times(0)).save(null);
    }

    @Test
    @DisplayName("Esta prueba valida la creacion de un usuario correcto")
    public void save_successTest() {
        User expectedUser = this.createTestUser(1);

        UserDTO userDTO = new UserDTO(1, "Goku", "Martinez", "goku@correo.com", "goku123", "319123123", "Goku casa", "Manager");

        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        User actualUser = userService.save(userDTO);

        Assertions.assertEquals(expectedUser, actualUser);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(User.class));
    }


    @Test
    @DisplayName("Esta prueba valida la elimincacion de un usuario que no existe")
    public void delete_InvalidIdTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.deleteById(1),
                "No existe un usuario registrado con el id: 1");

        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida la elimincacion de un usuario si existe en la bd")
    public void delete_validIdTest() {
        User expectedUser = this.createTestUser(1);
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(expectedUser));

        String actualMsg = userService.deleteById(Mockito.anyInt());

        Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyInt());
        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any());

        Assertions.assertTrue(actualMsg.contains("Se elimino exitosamente el usuario de id:"));
    }

    @Test
    @DisplayName("Esta prueba valida la obtencion de usuarios cuando no hay registros en la bd")
    public void findAll_emptyTest() {
        Mockito.when(repository.findAll()).thenReturn(Collections.emptyList());

        List<UserDTO> actualUsers = userService.getAllUsers();

        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertTrue(actualUsers.isEmpty());
    }

    @Test
    @DisplayName("Esta prueba valida la obtencion de usuarios cuando si hay registros en la bd")
    public void findAll_nonEmptyTest() {
        User userOne = new User(1, "Goku", "Martinez", "goku@correo.com", "goku123", "319123123", "Goku casa", "Manager", false);
        User userTwo = new User(2, "Maria", "Ramirez", "maria@correo.com", "maria123", "3123123", "Maria casa", "Customer", false);
        User userThree = new User(3, "Jose", "Duran", "jose@correo.com", "jose123", "423423", "Jose casa", "Customer", false);
        User userFour = new User(4, "Pedro", "Giraldo", "pedro@correo.com", "pedro123", "6786", "Pedro casa", "Customer", false);
        User userFive = new User(5, "Jesus", "Ronaldo", "jesus@correo.com", "jesus123", "4123", "Jesus casa", "Manager", false);
        List<User> dbUsers = List.of(userOne, userTwo, userThree, userFour, userFive);
        Mockito.when(repository.findAll()).thenReturn(dbUsers);

        List<UserDTO> actualUsers = userService.getAllUsers();

        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertFalse(actualUsers.isEmpty());
        Assertions.assertEquals(5, actualUsers.size());

        for (int i = 0; i < actualUsers.size(); i++) {
            User expected = dbUsers.get(i);
            UserDTO actual = actualUsers.get(i);
            this.assertEqualsEntityAndDto(expected, actual);
        }
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de un usuario nulo")
    public void update_nullTest() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.updateUser(null),
                "El usuario no existe");

        Mockito.verify(repository, Mockito.times(0)).findById(null);
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de un usuario con id invalido")
    public void update_invalidIdTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        Mockito.when(repository.findById(userDTO.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.updateUser(userDTO),
                "El usuario no existe");

        Mockito.verify(repository, Mockito.times(1)).findById(1);
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de un usuario con id valido")
    public void update_validIdTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);

        User userEntity = this.createTestUser(userDTO.getId());

        Mockito.when(repository.findById(userDTO.getId()))
                .thenReturn(
                        Optional.of(userEntity));

        userService.updateUser(userDTO);

        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).save(userEntity);
        this.assertEqualsEntityAndDto(userEntity, userDTO);
    }

    private User createTestUser(Integer id) {
        User expectedUser = new User();
        expectedUser.setType("Manager");
        expectedUser.setAddress("Address");
        expectedUser.setPassword("password");
        expectedUser.setCellPhone("312390");
        expectedUser.setEmail("mail@mail.com");
        expectedUser.setName("User Name");
        expectedUser.setLastName("Last Name");
        expectedUser.setId(id);
        expectedUser.setValidation(false);
        return expectedUser;
    }

    private void assertEqualsEntityAndDto(User entity, UserDTO dto){
        Assertions.assertEquals(entity.getId(), dto.getId());
        Assertions.assertEquals(entity.getAddress(), dto.getAddress());
        Assertions.assertEquals(entity.getEmail(), dto.getEmail());
        Assertions.assertEquals(entity.getCellPhone(), dto.getCellPhone());
        Assertions.assertEquals(entity.getName(), dto.getName());
        Assertions.assertEquals(entity.getPassword(), dto.getPassword());
        Assertions.assertEquals(entity.getType(), dto.getType());
    }

}
