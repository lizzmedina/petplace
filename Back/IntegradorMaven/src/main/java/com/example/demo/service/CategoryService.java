package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryDTO save(CategoryDTO categoryDTO){

       Optional<Category>  categoryExist =  categoryRepository.findByTitle(categoryDTO.getTitle());

       if(categoryExist.isPresent()){
           throw new RuntimeException("la categoria"+ categoryExist.get().getTitle()+" ya existe, prueba una diferente");
       }

        Category categoryEntity = categoryRepository.save(categoryMapper.mapToEntity(categoryDTO));
        categoryDTO.setId(categoryEntity.getId());
        return categoryDTO;
    }

    public List<CategoryDTO> findAll(){
        return categoryRepository.findAll().stream().map(categoryMapper::mapToDTO).toList();
    }

    /**
     * In the database the title field refers to the name
     *
     * @param name of the category to search for
     * @return Category object
     */
    public CategoryDTO findByName(String name) {
        return categoryRepository.findByTitle(name.toUpperCase()).map(categoryMapper::mapToDTO)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("La categoria [%s] no existe", name)));
    }

    public CategoryDTO edit(CategoryDTO categoryDTO){

        Optional<Category> category = this.categoryRepository.findById(categoryDTO.getId());
        if(category.isEmpty()){
            throw new  ResourceNotFoundException("No existe categoria con el id: " + categoryDTO.getId());
        }
        categoryRepository.save(categoryMapper.mapToEntity(categoryDTO));

        return categoryDTO;
    }


}
