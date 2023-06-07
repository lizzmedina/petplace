package com.example.demo.controller;

import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.RoleDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.Role;
import com.example.demo.repository.CityRepository;
import com.example.demo.service.CityService;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService service) {
        this.cityService = service;
    }

    @PostMapping()
    public CityDTO saveCity(@RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

    @GetMapping
    public List<CityDTO> getAllCities(){
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public CityDTO findCityById(@PathVariable("id") Integer id) {
        return cityService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCityById(@PathVariable("id") Integer id) {
        return cityService.deleteById(id, false);
    }

    @DeleteMapping("/force/{id}")
    public String deleteCityByIdForce(@PathVariable("id") Integer id) {
        return cityService.deleteById(id, true);
    }

    @PutMapping
    public ResponseEntity<String> updateCity(@RequestBody CityDTO cityDTO) {
        cityService.updateCity(cityDTO);
        return ResponseEntity.ok("Ciudad actualizada correctamente");

    }
}

