package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.City;
import com.example.demo.entity.PetDayCare;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PetDayCareRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class PetDayCareServiceTest {

    @Mock
    PetDayCareRepository petDayCareRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    PetDayCareService petDayCareService;

    @Test
    @DisplayName("Esta prueba valida la creación de un hotel nulo")
    public void save_nullTest(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> petDayCareService.save(null), "El hotel no puede ser nulo");
        Mockito.verify(petDayCareRepository,Mockito.times(0)).save(null);
    }

    @Test
    @DisplayName("Esta prueba valida la creación de un hotel correctamente")
    public void save_hotelTest(){
        //Given
        PetDayCareDTO petDayCareDTO = new PetDayCareDTO();
        petDayCareDTO.setType(new Category("canarios","Expertos en canarios",null));
        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getType().getTitle());

        PetDayCare expectedPetDayCare = new PetDayCare();
        expectedPetDayCare.setId(50);
        expectedPetDayCare.setName("Prueba hotel");
        expectedPetDayCare.setType(categoryMapper.mapToEntity(categoryDTO));
        expectedPetDayCare.setCapacity(30);
        expectedPetDayCare.setCity(new City("Ciudad"));
        expectedPetDayCare.setAddress("Ensayo");
        expectedPetDayCare.setImages(null);
        expectedPetDayCare.setCharacteristics(null);
        expectedPetDayCare.setBasicPrice(50.000);
        expectedPetDayCare.setHouseRules(null);
        expectedPetDayCare.setHealthAndSecurity(null);
        expectedPetDayCare.setCancellationPolicy(null);

        //When
        Mockito.when(petDayCareRepository.save(expectedPetDayCare)).thenReturn(expectedPetDayCare);//Cuando le dé guardar con el hotel anterior, retorneme el mismo hotel
        PetDayCare actualPetDayCare = petDayCareRepository.save(expectedPetDayCare);

        //Then: Mockito por favor verifique que al repositorio que es el mock, lo llamaron con un parametro null
        Assertions.assertEquals(expectedPetDayCare,actualPetDayCare);
        Mockito.verify(petDayCareRepository,Mockito.times(1)).save(expectedPetDayCare);//Verifique que se llamó al reposrotorio una vez y guardo la entidad.
    }

    @Test
    @DisplayName("Esta prueba valida la edición de un hotel nulo")
    public void edit_nullTest(){
        //given: Settear el escenario de pruebas, para probar con esos datos.

        //when: Cuando el hotel sea nulo, verificamos que se lanzó la exception
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> petDayCareService.edit(null), "El hotel no puede ser nulo");//Los assert son verificar. Se verifica que cuando se lance un save con hotel nulo, se debe verificar que lance la excepción que to quiero que lance

        //then: Mockito por favor verifique que al repositorio que es el mock, lo llamaron con un parametro null
        Mockito.verify(petDayCareRepository,Mockito.times(0)).save(null);//Verifique que nunca se llamó al reposrotorio porque el parametro es nulo.
    }

    /*@Test
    @DisplayName("Esta prueba valida la edición de un hotel con id invalido")
    public void edit_invalidIdTest() {
        //given:
        PetDayCareDTO petDayCareDTO = new PetDayCareDTO();
        petDayCareDTO.setId(50);

        //when:
        Mockito.when(petDayCareRepository.findById(petDayCareDTO.getId())).thenReturn(Optional.empty());
        PetDayCareDTO actualPetDayCareDTO = petDayCareService.edit(petDayCareDTO);

        //then
        Mockito.verify(petDayCareRepository, Mockito.times(1)).findById(50);
        Assertions.assertEquals(petDayCareDTO, actualPetDayCareDTO);

    }*/

    /*@Test
    @DisplayName("Esta prueba valida la edición de un hotel con id valido")
    public void edit_validIdTest() throws JsonProcessingException {
        //given:
        ObjectMapper objectMapper = new ObjectMapper();
        PetDayCareDTO petDayCareDTO = new PetDayCareDTO();

        petDayCareDTO.setId(1);
        petDayCareDTO.setName("Prueba hotel");
        petDayCareDTO.setCity("Ciudad");
        petDayCareDTO.setCapacity(30);
        petDayCareDTO.setCategoryName("title");
        petDayCareDTO.setBasicPrice(50.000);
        petDayCareDTO.setAddress("Ensayo");
        petDayCareDTO.setHouseRules(null);
        petDayCareDTO.setHealthAndSecurity(null);
        petDayCareDTO.setCancellationPolicy(null);

        PetDayCare expectedPetDayCare = new PetDayCare();
        expectedPetDayCare.setId(petDayCareDTO.getId());
        expectedPetDayCare.setName("Prueba hotel");
        expectedPetDayCare.setCapacity(30);
        expectedPetDayCare.setType(new Category("title", "description", "image"));
        expectedPetDayCare.setCity(new City("Ciudad"));
        expectedPetDayCare.setAddress("Ensayo");
        expectedPetDayCare.setImages(null);
        expectedPetDayCare.setCharacteristics(null);
        expectedPetDayCare.setBasicPrice(50.000);
        expectedPetDayCare.setHouseRules(null);
        expectedPetDayCare.setHealthAndSecurity(null);
        expectedPetDayCare.setCancellationPolicy(null);

        PetDayCare cloneExpected = objectMapper.readValue(objectMapper.writeValueAsString(expectedPetDayCare), PetDayCare.class);
        //when:
        Mockito.when(petDayCareRepository.findById(petDayCareDTO.getId()))
                .thenReturn(Optional.of(cloneExpected));

        PetDayCareDTO actualPetDayCareDTO = petDayCareService.edit(petDayCareDTO);

        //then
        Mockito.verify(petDayCareRepository, Mockito.times(1)).findById(1);
        Mockito.verify(petDayCareRepository, Mockito.times(1)).save(cloneExpected);

        Assertions.assertEquals(expectedPetDayCare.getId(),actualPetDayCareDTO.getId());
        Assertions.assertEquals(expectedPetDayCare.getName(),actualPetDayCareDTO.getName());
        Assertions.assertEquals(expectedPetDayCare.getType().getTitle(),actualPetDayCareDTO.getCategoryName());
        Assertions.assertEquals(expectedPetDayCare.getCapacity(),actualPetDayCareDTO.getCapacity());
        Assertions.assertEquals(expectedPetDayCare.getCity().getName(),actualPetDayCareDTO.getCity());
        Assertions.assertEquals(expectedPetDayCare.getAddress(),actualPetDayCareDTO.getAddress());
        Assertions.assertEquals(expectedPetDayCare.getImages(),actualPetDayCareDTO.getImages());
        Assertions.assertEquals(expectedPetDayCare.getCharacteristics(),actualPetDayCareDTO.getCharacteristics());
        Assertions.assertEquals(expectedPetDayCare.getBasicPrice(),actualPetDayCareDTO.getBasicPrice());

    }*/

}