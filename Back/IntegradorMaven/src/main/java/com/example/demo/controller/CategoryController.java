package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Integer id){
        return service.deleteById(id);
    }
}
