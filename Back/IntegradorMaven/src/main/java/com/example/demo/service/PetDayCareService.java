package com.example.demo.service;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.DTO.PetDayCareDetailDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PetDayCareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetDayCareService {

    private PetDayCareRepository repository;

    private List<PetDayCare> petDayCareList;

    private CategoryService categoryService;

    private CategoryMapper categoryMapper;

    @Autowired
    public PetDayCareService(PetDayCareRepository repository, List<PetDayCare> petDayCareList, CategoryService categoryService, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.petDayCareList = petDayCareList;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }


    public PetDayCareDTO edit(PetDayCareDTO petDayCareDTO){

        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getCategoryName());

        Optional<PetDayCare> namePetDayCareEntity = this.repository.findById(petDayCareDTO.getId());

        if(namePetDayCareEntity.isEmpty()){
            throw new ResourceNotFoundException("No existe guardería con el id: " + petDayCareDTO.getId());
        }

        PetDayCare petDayCareEntity = namePetDayCareEntity.get();
        petDayCareEntity.setName(petDayCareDTO.getName());
        petDayCareEntity.setType(categoryMapper.mapToEntity(categoryDTO));
        petDayCareEntity.setDetail(petDayCareDTO.getDetail());
        petDayCareEntity.setAddress(petDayCareDTO.getAddress());
        petDayCareEntity.setCity(petDayCareDTO.getCity());
        petDayCareEntity.setCapacity(petDayCareDTO.getCapacity());
        petDayCareEntity.setCharacteristics(petDayCareDTO.getCharacteristics());
        petDayCareEntity.setImages(petDayCareDTO.getImages());
        petDayCareEntity.setBasicPrice(petDayCareDTO.getBasicPrice());


        repository.save(petDayCareEntity);
        return petDayCareDTO;
    }


    public PetDayCareDTO save(PetDayCareDTO petDayCare){

        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCare.getCategoryName());

        PetDayCare newPetDayCare = new PetDayCare(
                petDayCare.getName(),
                categoryMapper.mapToEntity(categoryDTO),
                petDayCare.getCapacity(),
                petDayCare.getCity(),
                petDayCare.getAddress(),
                petDayCare.getDetail(),
                petDayCare.getImages(),
                petDayCare.getCharacteristics(),
                petDayCare.getBasicPrice()
        );

        repository.save(newPetDayCare);



        PetDayCareDTO petDayCareDTO = new PetDayCareDTO(
                newPetDayCare.getName(),
                newPetDayCare.getType().getTitle(),
                newPetDayCare.getCapacity(),
                newPetDayCare.getCity(),
                newPetDayCare.getAddress(),
                newPetDayCare.getDetail(),
                newPetDayCare.getImages(),
                newPetDayCare.getCharacteristics(),
                newPetDayCare.getBasicPrice());

        return petDayCareDTO;
    }

    public List<PetDayCare> findAll(){

    return repository.findAll().stream().collect(Collectors.toList());

    }

    public String delete(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(!petDayCare.isPresent()){
            throw new RuntimeException("La guarderia no fue encontrada");
        }
        repository.delete(petDayCare.get());
        return "El producto fue eliminado ";

    }

    public List<PetDayCare> findByCategory(Integer type){

        return repository.findByTypeId(type).stream().collect(Collectors.toList());

    }

    public PetDayCareDetailDTO detail(Integer id){
        Optional<PetDayCare> petDayCare = this.repository.findById(id);

        if(!petDayCare.isPresent()){ //si no esta presente, manda una excepcion
            throw new RuntimeException("La guarderia no fue encontrada");
        };


        PetDayCareDetailDTO detailPetDatCare = new PetDayCareDetailDTO(
                petDayCare.get().getName(),
                petDayCare.get().getType(),
                petDayCare.get().getCapacity(),
                petDayCare.get().getCity(),
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

}
