package com.example.demo.service;


import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.DTO.PetDayCareSaveDTO;
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
    private PetDayCareService petDayCareService;

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

    public PetDayCareSaveDTO save(PetDayCareSaveDTO petDayCareSaveDTO) {

        if(petDayCareSaveDTO == null){
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        }

        Optional<City> city = this.cityRepository.findByName(petDayCareSaveDTO.getCityName());
        Optional<Category> category = this.categoryRepository.findByTitle(petDayCareSaveDTO.getCategoryName());

        if(!city.isPresent()){
            throw new RuntimeException("La ciudad no se encuentra");
        }

        if(!category.isPresent()){
            throw new RuntimeException("La categoría no se encuentra");
        }

        PetDayCare newPetDayCare = new PetDayCare(
                petDayCareSaveDTO.getName(),
                category.get(),
                petDayCareSaveDTO.getCapacity(),
                city.get(),
                petDayCareSaveDTO.getAddress(),
                petDayCareSaveDTO.getDetail(),
                petDayCareSaveDTO.getImages(),
                petDayCareSaveDTO.getCharacteristics(),
                petDayCareSaveDTO.getBasicPrice(),
                petDayCareSaveDTO.getHouseRules(),
                petDayCareSaveDTO.getHealthAndSecurity(),
                petDayCareSaveDTO.getCancellationPolicy()

        );
        repository.save(newPetDayCare);

        PetDayCareSaveDTO savePetDayCare = new PetDayCareSaveDTO(
                petDayCareSaveDTO.getName(),
                petDayCareSaveDTO.getCategoryName(),
                petDayCareSaveDTO.getCapacity(),
                petDayCareSaveDTO.getCityName(),
                petDayCareSaveDTO.getAddress(),
                petDayCareSaveDTO.getDetail(),
                petDayCareSaveDTO.getImages(),
                petDayCareSaveDTO.getCharacteristics(),
                petDayCareSaveDTO.getBasicPrice(),
                petDayCareSaveDTO.getHouseRules(),
                petDayCareSaveDTO.getHealthAndSecurity(),
                petDayCareSaveDTO.getCancellationPolicy()
        );

        petDayCareSaveDTO.setId(savePetDayCare.getId());

        return savePetDayCare;
    }

    public PetDayCareDTO saveBD(PetDayCareDTO petDayCare) {

        if (petDayCare == null) {
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        }

        City city = cityRepository.findByName(petDayCare.getCity().getName()).get();
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

    public PetDayCareDTO edit(PetDayCareDTO petDayCareDTO){

        if(petDayCareDTO != null){


            Optional<PetDayCare> namePetDayCareEntity = this.repository.findById(petDayCareDTO.getId());


            if(namePetDayCareEntity.isPresent()){
                PetDayCare petDayCareEntity = namePetDayCareEntity.get();

                if(petDayCareDTO.getName() != null && !petDayCareDTO.getName().isEmpty()){
                    petDayCareEntity.setName(petDayCareDTO.getName());
                }
                if(petDayCareDTO.getDetail() != null &&   !petDayCareDTO.getDetail().isEmpty()){
                    petDayCareEntity.setDetail(petDayCareDTO.getDetail());
                }
                if(petDayCareDTO.getAddress() != null && !petDayCareDTO.getAddress().isEmpty()){
                    petDayCareEntity.setAddress(petDayCareDTO.getAddress());
                }
                if(petDayCareDTO.getCapacity() != null && petDayCareDTO.getCapacity() > 0){
                    petDayCareEntity.setCapacity(petDayCareDTO.getCapacity());
                }
                if(petDayCareDTO.getCharacteristics() != null && !petDayCareDTO.getCharacteristics().isEmpty()){
                    petDayCareEntity.setCharacteristics(petDayCareDTO.getCharacteristics());
                }
                if(petDayCareDTO.getImages() !=null && !petDayCareDTO.getImages().isEmpty()){
                    petDayCareEntity.setImages(petDayCareDTO.getImages());
                }
                if(petDayCareDTO.getBasicPrice() > 0){
                    petDayCareEntity.setBasicPrice(petDayCareDTO.getBasicPrice());
                }
                if(petDayCareDTO.getHouseRules() != null && !petDayCareDTO.getHouseRules().isEmpty()){
                    petDayCareEntity.setHouseRules(petDayCareDTO.getHouseRules());
                }

                if (petDayCareDTO.getType() !=null && petDayCareDTO.getType().getTitle() !=null && !petDayCareDTO.getType().getTitle().isEmpty()){
                    CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getType().getTitle());
                    petDayCareEntity.setType(categoryMapper.mapToEntity(categoryDTO));
                }

                if(petDayCareDTO.getCity() !=null && petDayCareDTO.getCity().getName() !=null && !petDayCareDTO.getCity().getName().isEmpty()){
                    CityDTO cityDTO = this.cityService.findByName(petDayCareDTO.getCity().getName());
                    petDayCareEntity.setCity(cityMapper.mapToEntity(cityDTO));
                }

                PetDayCare petDayCare = repository.save(petDayCareEntity);

                petDayCareDTO.setName(petDayCare.getName());
                petDayCareDTO.setType(petDayCare.getType());
                petDayCareDTO.setAddress(petDayCare.getAddress());
                petDayCareDTO.setCity(cityMapper.mapToDto(petDayCare.getCity()));
                petDayCareDTO.setDetail(petDayCare.getDetail());
                petDayCareDTO.setCancellationPolicy(petDayCare.getCancellationPolicy());
                petDayCareDTO.setImages(petDayCare.getImages());
                petDayCareDTO.setHealthAndSecurity(petDayCare.getHealthAndSecurity());
                petDayCareDTO.setCancellationPolicy(petDayCare.getCancellationPolicy());
                petDayCareDTO.setHouseRules(petDayCare.getHouseRules());
                petDayCareDTO.setBasicPrice(petDayCare.getBasicPrice());
                petDayCareDTO.setCharacteristics(petDayCare.getCharacteristics());
                petDayCareDTO.setId(petDayCare.getId());
            }
            return petDayCareDTO;
        }else if (petDayCareDTO == null){
            throw new IllegalArgumentException("El hotel no puede ser nulo");
        } else {
            throw new ResourceNotFoundException("No existe guardería con el id: " + petDayCareDTO.getId());
        }

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
                //category.get().getTitle(),
                petDayCare.get().getCapacity(),
                cityMapper.mapToDto(petDayCare.get().getCity()),
                petDayCare.get().getAddress(),
                petDayCare.get().getDetail(),
                petDayCare.get().getImages(),
                petDayCare.get().getCharacteristics(),
                petDayCare.get().getBasicPrice(),
                petDayCare.get().getHouseRules(),
                petDayCare.get().getHealthAndSecurity(),
                petDayCare.get().getCancellationPolicy()
        );
        petDayCareDTO.setId(id);

        return petDayCareDTO;
    }

    public void deleteAll(){
        repository.findAll().stream().forEach(repository::delete);//Elimina todos los datos de la BD
    }

    public Optional<PetDayCare> findById(Integer id) {
        return repository.findById(id);
    }
}