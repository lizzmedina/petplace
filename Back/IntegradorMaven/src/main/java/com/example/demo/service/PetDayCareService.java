package com.example.demo.service;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.DTO.PetDayCareDetailDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.CityMapper;
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

    private CategoryMapper categoryMapper;

    private CityRepository cityRepository;

    private CityMapper cityMapper;

    @Autowired
    public PetDayCareService(PetDayCareRepository repository, CategoryService categoryService,
                             CategoryMapper categoryMapper, CityRepository cityRepository, CityMapper cityMapper) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    public PetDayCareDTO edit(PetDayCareDTO petDayCareDTO){

        if(petDayCareDTO != null){
            CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getCategoryName());
            Optional<PetDayCare> namePetDayCareEntity = this.repository.findById(petDayCareDTO.getId());

            if(namePetDayCareEntity.isPresent()){
                PetDayCare petDayCareEntity = namePetDayCareEntity.get();
                petDayCareEntity.setName(petDayCareDTO.getName());
                petDayCareEntity.setType(categoryMapper.mapToEntity(categoryDTO));
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

        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCare.getCategoryName());

        PetDayCare newPetDayCare = new PetDayCare(
                petDayCare.getName(),
                categoryMapper.mapToEntity(categoryDTO),
                petDayCare.getCapacity(),
                city,
                petDayCare.getAddress(),
                petDayCare.getDetail(),
                petDayCare.getImages(),
                petDayCare.getCharacteristics(),
                petDayCare.getBasicPrice()
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

    public PetDayCareDetailDTO detail(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(petDayCare.isEmpty()){ //si no esta presente, lanza una excepcion
            throw new ResourceNotFoundException("La guarderia no fue encontrada");
        }


        PetDayCareDetailDTO detailPetDatCare = new PetDayCareDetailDTO(
                petDayCare.get().getName(),
                petDayCare.get().getType(),
                petDayCare.get().getCapacity(),
                petDayCare.get().getCity().getName(),
                petDayCare.get().getAddress(),
                petDayCare.get().getDetail(),
                petDayCare.get().getImages(),
                petDayCare.get().getCharacteristics(),
                petDayCare.get().getBasicPrice()
        );


        return detailPetDatCare;

    }

    public void deleteAll(){
        repository.findAll().stream().forEach(repository::delete);//Elimina todos los datos de la BD
    }

    public Optional<PetDayCare> findById(Integer id) {
        return repository.findById(id);
    }
}