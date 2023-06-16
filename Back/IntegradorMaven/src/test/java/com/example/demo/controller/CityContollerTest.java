package com.example.demo.controller;

import com.example.demo.DTO.CityDTO;
import com.example.demo.service.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CityContollerTest {

    @Mock
    CityService cityService;

    @InjectMocks CityController cityController;

    CityDTO requestDto = new CityDTO("Cali");
    CityDTO responseDto = new CityDTO(1, "Cali");

    @Test
    public void saveCityTest(){
        Mockito.when(cityService.save(null)).thenReturn(null);
        Mockito.when(cityService.save(requestDto)).thenReturn(responseDto);

        CityDTO actual = cityController.saveCity(null);

        Assertions.assertNull(actual);

        actual = cityController.saveCity(requestDto);

        Assertions.assertEquals(responseDto, actual);

        Mockito.verify(cityService, Mockito.times(1)).save(null);
        Mockito.verify(cityService, Mockito.times(1)).save(requestDto);
    }

    @Test
    public void getAllCitiesTest(){
        cityController.getAllCities();
        Mockito.verify(cityService).getAllCities();
    }

    @Test
    public void findCityByIdTest(){
        Mockito.when(cityService.findById(1)).thenReturn(responseDto);

        CityDTO actual = cityController.findCityById(1);

        Assertions.assertEquals(responseDto, actual);

        Mockito.verify(cityService).findById(1);
    }

    @Test
    public void deleteCityByIdTest(){
        Mockito.when(cityService.deleteById(1, false)).thenReturn("No se pudo eliminar la ciudad");

        String actual = cityController.deleteCityById(1);

        Assertions.assertEquals("No se pudo eliminar la ciudad", actual);

        Mockito.verify(cityService).deleteById(1, false);
    }

    @Test
    public void deleteCityByIdForceTest(){
        Mockito.when(cityService.deleteById(1, true)).thenReturn("Eliminado exitosamente");

        String actual = cityController.deleteCityByIdForce(1);

        Assertions.assertEquals("Eliminado exitosamente", actual);

        Mockito.verify(cityService).deleteById(1, true);
    }

    @Test
    public void updateCityTest(){
        Mockito.when(cityService.updateCity(requestDto)).thenReturn(responseDto);

        CityDTO actual = cityController.updateCity(requestDto);

        Assertions.assertEquals(responseDto, actual);

        Mockito.verify(cityService).updateCity(requestDto);
    }
}

