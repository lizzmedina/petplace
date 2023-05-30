package com.example.demo.service;

import com.example.demo.entity.Pet;
import com.example.demo.repository.PetRepository;
import org.aspectj.lang.annotation.Before;
import org.hibernate.query.SelectionQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService;

    @Test
    @DisplayName("Esta prueba valida la eliminacion de una mascota existente")
    public void delete_existingPetTest() {
        // Crear una mascota de prueba
        Pet pet = new Pet(1, "string", "string", "string");
        pet.setId(1);

        // Configurar el comportamiento del repositorio
        Mockito.when(petRepository.findById(any())).thenReturn(Optional.of(pet));
        Mockito.when(petRepository.save(pet)).thenReturn(pet);

        // Llamar al método delete
        petService.delete(pet.getId());

        // Verificar que se haya llamado al método delete del repositorio con la mascota correcta
        Mockito.verify(petRepository, Mockito.times(1)).delete(pet);
    }
}

