package com.example.demo.service;

import com.example.demo.entity.Pet;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @Mock
    PetRepository repository;

    @InjectMocks
    PetService petService;

    @Test
    @DisplayName("Esta prueba valida de eliminación de una mascota que no existe")
    public void delete_ValidIdTest() {
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> petService.deleteById(1), "No existe una mascota creada con el id 1");
        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida de eliminacion de una mascota que si existe en la bd")
    public void delete_ValidIdTest2() {
        //given -> dado .... un listado de 5 perros de la manera pacos1,asd2,1234.
        //when -> cuando .... yo elimine el perro llamado paco1
        //then -> entonces .. el listado resultante debe ser 123,123123,

        // given -> dado que en la bd existe una mascota con 1 , paco, perro, 23k
        Pet expected = new Pet(1, "Paco", "Perro", "23k");
        Mockito.when(repository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(expected));

        //when ... entonces cuando borre el 1 id
        petService.deleteById(1);

        // then .. entonces se ejecutaron los metodos de buscar el id 1, y eliminar por 1 id
        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).delete(expected);
    }



    }



