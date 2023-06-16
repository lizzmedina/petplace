package com.example.demo.service;


import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.PetDayCare;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicatedNameException;
import com.example.demo.exception.ReferencedCityException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CityMapper;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.PetDayCareRepository;
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
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityMapper cityMapper;
    @Mock
    private PetDayCareRepository petDayCareRepository;

    @InjectMocks
    CityService cityService;

    @Test
    @DisplayName("Esta prueba valida la eliminación de una ciudad que no existe")
    public void delete_notExistingCityTest() {
        int id = 12;
        Mockito.when(cityRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> cityService.deleteById(id, true), "No existe una ciudad creada con el id " + id);
        Mockito.verify(cityRepository, Mockito.times(1)).findById(id);
        Mockito.verify(cityRepository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida la eliminación de una ciudad existente referenciada")
    public void delete_ValidCityWithReferenceCityTest() {
        City expectedCity = new City("Barranquilla");
        expectedCity.setId(1);
        Optional<City> expectedCityOpt = Optional.of(expectedCity);

        Mockito.when(cityRepository.findById(1)).thenReturn(expectedCityOpt);
        Mockito.when(cityMapper.mapToDto(expectedCity)).thenCallRealMethod();
        Mockito.when(petDayCareRepository.findAllByCityId(1)).thenReturn(List.of(new PetDayCare()));

        Assertions.assertThrows(ReferencedCityException.class,
                () -> cityService.deleteById(1, false), "no se pudo");

        Mockito.verify(cityRepository, Mockito.times(1)).findById(expectedCity.getId());
        Mockito.verify(petDayCareRepository, Mockito.times(1)).findAllByCityId(expectedCity.getId());
        Mockito.verify(cityMapper).mapToDto(expectedCity);
        Mockito.verify(cityRepository, Mockito.times(0)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida la eliminación de una ciudad existente NO referenciada")
    public void delete_ValidCityWithoutReferenceCityTest() {
        City expectedCity = new City("Barranquilla");
        expectedCity.setId(1);
        Optional<City> expectedCityOpt = Optional.of(expectedCity);

        Mockito.when(cityRepository.findById(1)).thenReturn(expectedCityOpt);
        Mockito.when(petDayCareRepository.findAllByCityId(1)).thenReturn(Collections.emptyList());

        cityService.deleteById(1, false);

        Mockito.verify(cityRepository, Mockito.times(1)).findById(expectedCity.getId());
        Mockito.verify(petDayCareRepository, Mockito.times(1)).findAllByCityId(expectedCity.getId());
        Mockito.verify(cityRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    @DisplayName("Esta prueba valida la creacion de una ciudad nula")
    public void save_nullCityTest() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> cityService.save(null), "La ciudad no puede ser nula");

        Mockito.verify(cityRepository, Mockito.times(0)).save(null);
    }

    @Test
    @DisplayName("Esta prueba valida la creacion de una ciudad correcta")
    public void save_successCityTest() {

        City expectedCity = new City();
        expectedCity.setId(1);
        expectedCity.setName("Ciudad creada");

        CityDTO dto = new CityDTO("Ciudad creada");

        Mockito.when(cityRepository.save(cityMapper.mapToEntity(dto))).thenReturn(expectedCity);

        CityDTO actualCity = cityService.save(dto);

        Assertions.assertEquals(1, actualCity.getId());
        Assertions.assertEquals("Ciudad creada", actualCity.getName());
        Assertions.assertNull(actualCity.getPetDayCareDTOSet());
        Mockito.verify(cityRepository, Mockito.times(1)).save(cityMapper.mapToEntity(dto));
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de una ciudad nula")
    public void update_nullCityTest() {
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> cityService.updateCity(null),
                "La ciudad no existe");

        Mockito.verify(cityRepository, Mockito.times(0)).findById(null);
    }

    @Test
    @DisplayName("Esta prueba valida la actualizacion de una ciudad correctamente")
    public void update_validCityTest() {
        City found = new City("Cali");
        found.setId(1);

        Mockito.when(cityRepository.findById(1)).thenReturn(Optional.of(found));
        Mockito.doCallRealMethod().when(cityMapper).mapToDto(found);

        CityDTO dto = cityMapper.mapToDto(found);
        dto.setName("Medellín");

        CityDTO actual = cityService.updateCity(dto);

        Assertions.assertEquals(1, actual.getId());
        Assertions.assertEquals("Medellín", actual.getName());

        Mockito.verify(cityRepository).findById(1);
        Mockito.verify(cityRepository).save(found);
    }


    @Test
    @DisplayName("Esta prueba valida la obtencion de ciudades cuando no hay registros en la bd")
    public void findAll_emptyCityTest() {
        Mockito.when(cityRepository.findAll()).thenReturn(Collections.emptyList());

        List<CityDTO> actualCities = cityService.getAllCities();

        Mockito.verify(cityRepository, Mockito.times(1)).findAll();
        Assertions.assertTrue(actualCities.isEmpty());
    }

  @Test
  @DisplayName("Validar buscar por Id")
  public void findByIdTest() {
    City found = new City("Cali");
    found.setId(1);

    Mockito.doCallRealMethod().when(cityMapper).mapToDto(found);
    Mockito.when(cityRepository.findById(1)).thenReturn(Optional.of(found));

    CityDTO actual = cityService.findById(1);

    Mockito.verify(cityMapper).mapToDto(found);
    Mockito.verify(cityRepository).findById(1);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Cali", actual.getName());
  }

  @Test
  @DisplayName("Validar buscar por nombre")
  public void findByNameTest() {
    String name = "Cali";

    City found = new City(name);
    found.setId(1);

    Mockito.doCallRealMethod().when(cityMapper).mapToDto(found);
    Mockito.when(cityRepository.findByName(name)).thenReturn(Optional.of(found));

    CityDTO actual = cityService.findByName(name);

    Mockito.verify(cityMapper).mapToDto(found);
    Mockito.verify(cityRepository).findByName(name);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Cali", actual.getName());
  }

  @Test
  @DisplayName("Esta prueba valida la creacion de una ciudad que ya existe")
  public void save_duplicateCityTest() {

    City found = new City("Cali");
    found.setId(1);

    Mockito.when(cityRepository.findByName("Cali")).thenReturn(Optional.of(found));

    Assertions.assertThrows(
        DuplicatedNameException.class,
        () -> cityService.save(new CityDTO("Cali")),
        "Ya existe una ciudad con el nombre: Cali");

    Mockito.verify(cityRepository, Mockito.times(0)).save(Mockito.any());
  }
}




