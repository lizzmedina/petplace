package com.example.demo.controller;

import com.example.demo.configuration.data.loader.DataLoaderComponent;
import com.example.demo.service.PetDayCareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadData")
public class DataLoaderController {

    @Autowired
    private DataLoaderComponent dataLoaderComponent;

    @Autowired
    private PetDayCareService petDayCareService;

    @GetMapping("/petDayCare")
    public void reloadPetDayCare(){
        petDayCareService.deleteAll();
        dataLoaderComponent.loadInitialPetDayCareData();
    }

    @GetMapping("/cities")
    public void reloadCities(){
        dataLoaderComponent.loadInitialCities();
    }
}
