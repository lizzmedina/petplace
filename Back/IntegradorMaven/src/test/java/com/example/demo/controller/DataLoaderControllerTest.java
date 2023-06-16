package com.example.demo.controller;

import com.example.demo.configuration.data.loader.DataLoaderComponent;
import com.example.demo.service.PetDayCareService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataLoaderControllerTest {
  @Mock DataLoaderComponent dataLoaderComponent;

  @Mock PetDayCareService petDayCareService;

  @InjectMocks DataLoaderController dataLoaderController;

  @Test
  public void reloadPetDayCareTest() {
    dataLoaderController.reloadPetDayCare();
    Mockito.verify(petDayCareService).deleteAll();
    Mockito.verify(dataLoaderComponent).loadInitialPetDayCareData();
  }

  @Test
  public void reloadCitiesTest() {
    dataLoaderController.reloadCities();
    Mockito.verify(dataLoaderComponent).loadInitialCities();
  }
}
