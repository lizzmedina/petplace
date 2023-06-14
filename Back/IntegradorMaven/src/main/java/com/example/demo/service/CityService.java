package com.example.demo.service;

import com.example.demo.DTO.CityDTO;
import com.example.demo.entity.City;
import com.example.demo.exception.DuplicatedNameException;
import com.example.demo.exception.ReferencedCityException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CityMapper;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.PetDayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CityService {
    private CityRepository cityRepository;
    private CityMapper cityMapper;
    private PetDayCareRepository petDayCareRepository;


    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapper, PetDayCareRepository petDayCareRepository) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.petDayCareRepository = petDayCareRepository;

    }

    public CityDTO findById(Integer id) {
        return cityMapper.mapToDto(cityRepository.findById(id).get());
    }


    public CityDTO findByName(String name){
        return cityMapper.mapToDto(cityRepository.findByName(name).get());
    }

    public List<CityDTO> getAllCities(){
        return cityRepository.findAll().stream().map(cityMapper::mapToDto).toList();
    }

    public CityDTO save(CityDTO cityDTO) {
        if (cityDTO == null) {
            throw new ResourceNotFoundException("La ciudad no puede ser nula");
        }

        Optional<City> found = cityRepository.findByName(cityDTO.getName());
        if(found.isPresent()){
            throw new DuplicatedNameException("Ya existe una ciudad con el nombre: "+found.get().getName());
        }

        City created = cityRepository.save(cityMapper.mapToEntity(cityDTO));
        cityDTO.setId(created.getId());
        return cityDTO;
    }

    public String deleteById(Integer id, boolean force) {
        Optional<City> cityopt = this.cityRepository.findById(id);

        if (cityopt.isEmpty()) {
            throw new ResourceNotFoundException("No existe una ciudad registrada con el id: " + id);
        }

        City found = cityopt.get();

        if (!force &&
                (found.getPetDayCareSet() != null ||
                !petDayCareRepository.findAllByCityId(found.getId()).isEmpty())){
            throw new ReferencedCityException(cityMapper.mapToDto(found));
        }

        cityRepository.delete(found);
        return String.format("Se elimino exitosamente la ciudad con id: %s, nombre: %s", found.getId(), found.getName());
    }

    public CityDTO updateCity(CityDTO cityDTO) {
        if (cityDTO != null) {
            Optional<City> cityOpt = cityRepository.findById(cityDTO.getId());
            if (cityOpt.isPresent()) {
                City city = cityOpt.get();
                city.setName(cityDTO.getName());
               cityRepository.save(city);
               return cityDTO;
            }
        }
        throw new ResourceNotFoundException("la ciudad no existe");
    }

}

