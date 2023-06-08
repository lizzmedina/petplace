package com.example.demo.service;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.City;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.CityMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.PetDayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetDayCareService {

    private PetDayCareRepository repository;

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    private CityRepository cityRepository;

    private CityMapper cityMapper;
    private CityService cityService;

    @Autowired
    public PetDayCareService(PetDayCareRepository repository, CategoryService categoryService, CategoryRepository categoryRepository, CategoryMapper categoryMapper, CityRepository cityRepository, CityMapper cityMapper, CityService cityService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.cityService = cityService;
    }

    public PetDayCareDTO edit(PetDayCareDTO petDayCareDTO){

        if(petDayCareDTO != null){
            CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getType().getTitle());
            Optional<PetDayCare> namePetDayCareEntity = this.repository.findById(petDayCareDTO.getId());
            CityDTO cityDTO = this.cityService.findByName(petDayCareDTO.getCity());

            if(namePetDayCareEntity.isPresent()){
                PetDayCare petDayCareEntity = namePetDayCareEntity.get();
                petDayCareEntity.setName(petDayCareDTO.getName());
                petDayCareEntity.setType(categoryMapper.mapToEntity(categoryDTO));
                petDayCareEntity.setCity(cityMapper.mapToEntity(cityDTO));
                petDayCareEntity.setDetail(petDayCareDTO.getDetail());
                petDayCareEntity.setAddress(petDayCareDTO.getAddress());
                petDayCareEntity.setCapacity(petDayCareDTO.getCapacity());
                petDayCareEntity.setCharacteristics(petDayCareDTO.getCharacteristics());
                petDayCareEntity.setImages(petDayCareDTO.getImages());
                petDayCareEntity.setBasicPrice(petDayCareDTO.getBasicPrice());

                repository.save(petDayCareEntity);
            }
            return petDayCareDTO;
        }else if (petDayCareDTO == null){
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        } else {
            throw new ResourceNotFoundException("No existe guardería con el id: " + petDayCareDTO.getId());
        }

    }


    public PetDayCareDTO save(PetDayCareDTO petDayCare) {

        if (petDayCare == null) {
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        }

        City city = cityRepository.findByName(petDayCare.getCity()).get();
        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCare.getType().getTitle());

        PetDayCare newPetDayCare = new PetDayCare(
                petDayCare.getName(),
                categoryMapper.mapToEntity(categoryDTO),
                petDayCare.getCapacity(),
                city,
                petDayCare.getAddress(),
                petDayCare.getDetail(),
                petDayCare.getImages(),
                petDayCare.getCharacteristics(),
                petDayCare.getBasicPrice(),
                petDayCare.getHouseRules(),
                petDayCare.getHealthAndSecurity(),
                petDayCare.getCancellationPolicy()
        );

        newPetDayCare = repository.save(newPetDayCare);
        petDayCare.setId(newPetDayCare.getId());
        return petDayCare;
    }

    public List<PetDayCare> findAll(){
        return repository.findAll();
    }

    public String delete(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(!petDayCare.isPresent()){
            throw new ResourceNotFoundException("La guarderia no fue encontrada");
        }
        repository.delete(petDayCare.get());
        return "El producto fue eliminado ";

    }

    public List<PetDayCare> findByCategory(Integer type){
        return repository.findByTypeId(type);
    }

    public PetDayCareDTO detail(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);
        Optional<Category> category = this.categoryRepository.findById(petDayCare.get().getType().getId());


        if(petDayCare.isEmpty()){ //si no esta presente, lanza una excepcion
            throw new ResourceNotFoundException("La guarderia no fue encontrada");
        }

        //CityDTO cityDTO = cityService.findByName(petDayCare.get().getCity().getName());

        PetDayCareDTO petDayCareDTO= new PetDayCareDTO(
                petDayCare.get().getName(),
                category.get(),
                petDayCare.get().getCapacity(),
                petDayCare.get().getCity().getName(),
                petDayCare.get().getAddress(),
                petDayCare.get().getDetail(),
                petDayCare.get().getImages(),
                petDayCare.get().getCharacteristics(),
                petDayCare.get().getBasicPrice(),
                petDayCare.get().getHouseRules(),
                petDayCare.get().getHealthAndSecurity(),
                petDayCare.get().getCancellationPolicy()
        );

        return petDayCareDTO;
    }

    public void deleteAll(){
        repository.findAll().stream().forEach(repository::delete);//Elimina todos los datos de la BD
    }

    public Optional<PetDayCare> findById(Integer id) {
        return repository.findById(id);
    }
}