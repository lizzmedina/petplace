package com.example.demo.configuration.data.loader;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Category;
import com.example.demo.repository.PetDayCareRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PetDayCareService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoaderComponent {

    @Autowired
    private PetDayCareService petDayCareService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    public void loadInitialPetDayCareData() {
        //petDayCareService.deleteAll();//Solo para hacer pruebas
        if (petDayCareService.findAll().isEmpty()) {
            System.out.println("loading pet day care data...");
            List<PetDayCareDTO> petDayCareList = JsonHelper.readJsonFromFile("petdaycare_data.json", new TypeReference<>() {
            });
            petDayCareList.forEach(petDayCareService::save);
        } else {
            System.out.println("pet day care data already exists, skipping creation...");
        }
    }

    public void loadInitialCategoriesData() {
        if (categoryService.findAll().isEmpty()) {
            System.out.println("loading categories data...");
            List<CategoryDTO> petDayCareList = JsonHelper.readJsonFromFile("categories_data.json", new TypeReference<>() {
            });
            petDayCareList.forEach(categoryService::save);
        } else {
            System.out.println("category data already exists, skipping creation...");
        }
    }

    public void loadInitialUserData() {
        //petDayCareService.deleteAll();//Solo para hacer pruebas
        if (userService.getAllUsers().isEmpty()) {
            System.out.println("loading user data...");
            List<UserDTO> userDTOList = JsonHelper.readJsonFromFile("user_data.json", new TypeReference<>() {
            });
            userDTOList.forEach(userService::save);
        } else {
            System.out.println("user data already exists, skipping creation...");
        }
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadInitialCategoriesData();
        loadInitialPetDayCareData();
        loadInitialUserData();
    }
}