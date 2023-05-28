package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping()
    public CategoryDTO save(@RequestBody CategoryDTO categoryDTO){
        return service.save(categoryDTO);
    }

    @GetMapping("/all")
    public List<CategoryDTO> categoryList(){
        return service.findAll();
    }

    @PutMapping("/edit")
    public CategoryDTO edit(@RequestBody CategoryDTO categoryDTO){
        return service.edit(categoryDTO);
    }

    @GetMapping("/{id}")
    public Category finById(@PathVariable("id") Integer id){
        return service.finById(id);
    }
}
