package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.User;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    CategoryRepository repository;

    @Mock
    CategoryMapper mapper;

    @InjectMocks
    CategoryService service;

    @Test
    @DisplayName("Esta prueba la creacion de una categoria nula")
    public void save_nullTest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> service.save(null), "la categoria no puede ser nulo");

        Mockito.verify(repository, Mockito.times(0)).save(null);
    }

//    @Test
//    @DisplayName("Esta prueba valida la creacion de un usuario correcto")
//    public void save_successTest() {
//        Category expectedCategory = this.createTestCategory(1);
//
//        CategoryDTO categoryDTO = new CategoryDTO(1, "Hamster", "guarderia de roedores", "url.com");
//
//        Mockito.when(repository.save(Mockito.any(Category.class))).thenReturn(expectedCategory);
//
//       Category actualCategory = mapper.mapToEntity(service.save(categoryDTO));
//
//        Assertions.assertEquals(expectedCategory, actualCategory);
//        Mockito.verify(repository, Mockito.times(1)).save(Mockito.eq(expectedCategory));
//    }

private Category createTestCategory(Integer id){
        Category expectedCategory = new Category();
        expectedCategory.setTitle("Title");
        expectedCategory.setDescription("Description");
        expectedCategory.setImage("image");
        return expectedCategory;
}



}
