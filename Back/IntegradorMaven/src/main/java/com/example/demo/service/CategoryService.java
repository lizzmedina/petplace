package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category){
        category.setTitle(category.getTitle().toUpperCase());
        return categoryRepository.save(category);

    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    /**
     * In the database the title field refers to the name
     *
     * @param name of the category to search for
     * @return Category object
     */
    public Category findByName(String name) {
        return categoryRepository.findByTitle(name.toUpperCase())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("La categoria [%s] no existe", name)));
    }
}
