package com.example.demo.mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryMapper() {
    }

    public CategoryDTO mapToDTO(Category categoryEntity){
        if(categoryEntity == null){
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setTitle(categoryEntity.getTitle());
        categoryDTO.setDescription(categoryEntity.getDescription());
        categoryDTO.setImage(categoryEntity.getImage());

        return categoryDTO;
    }

    public Category mapToEntity(CategoryDTO categoryDTO){
        if(categoryDTO == null){
            return null;
        }

        Category categoryEntity = new Category();
        categoryEntity.setId(categoryDTO.getId());
        categoryEntity.setTitle(categoryDTO.getTitle());
        categoryEntity.setDescription(categoryDTO.getDescription());
        categoryEntity.setImage(categoryDTO.getImage());

        return categoryEntity;
    }
}
