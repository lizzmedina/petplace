package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import com.example.demo.exception.ResourceNotFoundException;
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

import java.util.Optional;

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

    @Test
    @DisplayName("Esta prueba valida la creacion de una categoria correcta")
    public void save_successTest() {
        Category expectedCategory = this.createTestCategory(1);

        CategoryDTO categoryDTO = new CategoryDTO(1, "Hamster", "guarderia de roedores", "url.com","icon");
        Category mappedCategory = mapper.mapToEntity(categoryDTO);

        Mockito.when(repository.save(mappedCategory)).thenReturn(expectedCategory);

        CategoryDTO actualCategory = service.save(categoryDTO);

        Mockito.verify(repository, Mockito.times(1)).save(mappedCategory);

        Assertions.assertEquals(expectedCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectedCategory.getDescription(), actualCategory.getDescription());
        Assertions.assertEquals(expectedCategory.getTitle(), actualCategory.getTitle());
        Assertions.assertEquals(expectedCategory.getImage(), actualCategory.getImage());
        Assertions.assertEquals(expectedCategory.getIcon(), actualCategory.getIcon());
    }

    @Test
    @DisplayName("Esta prueba valida la creacion de una categoria existente")
    public void save_existingCategoryTest() {

        CategoryDTO categoryDTO = new CategoryDTO(1, "Hamster", "guarderia de roedores", "url.com","icon");
        Mockito.doCallRealMethod().when(mapper).mapToEntity(categoryDTO);
        Category mappedCategory = mapper.mapToEntity(categoryDTO);
        Mockito.when(repository.findByTitle("Hamster")).thenReturn(Optional.of(mappedCategory));

        Assertions.assertThrows( RuntimeException.class,() -> service.save(categoryDTO));

        Mockito.verify(repository, Mockito.times(0)).save(mappedCategory);

    }

    @Test
    @DisplayName("Esta prueba valida la edición de una categoria nula")
    public void edit_nullTest(){
        Assertions.assertThrows(NullPointerException.class,
                () -> service.edit(null), "la categoria no puede ser nulo");

        Mockito.verify(repository, Mockito.times(0)).save(null);
    }


    @Test
    @DisplayName("Esta prueba valida la creacion de una categoria correcta")
    public void edit_successTest() {

        Category expectedCategory = this.createTestCategory(1);

        CategoryDTO categoryDTO = new CategoryDTO(1, "Hamster", "guarderia de roedores", "url.com","icon");

        Category mappedCategory = mapper.mapToEntity(categoryDTO);
        Mockito.when(repository.save(mappedCategory)).thenReturn(expectedCategory);

        //when:
        Mockito.when(repository.findById(categoryDTO.getId()))
                .thenReturn(Optional.of(expectedCategory));

        CategoryDTO editCategory = service.edit(categoryDTO);

        //then:
        Mockito.verify(repository, Mockito.times(1)).findById(1);
        Mockito.verify(repository, Mockito.times(1)).save(mappedCategory);

        Assertions.assertEquals(expectedCategory.getId(), editCategory.getId());
        Assertions.assertEquals(expectedCategory.getDescription(), editCategory.getDescription());
        Assertions.assertEquals(expectedCategory.getTitle(), editCategory.getTitle());
        Assertions.assertEquals(expectedCategory.getImage(), editCategory.getImage());
        Assertions.assertEquals(expectedCategory.getIcon(),editCategory.getIcon());
    }

  @Test
  public void edit_exceptionTest() {
    Mockito.when(repository.findById(1)).thenReturn(Optional.empty());
    Assertions.assertThrows(
        ResourceNotFoundException.class,
        () -> service.edit(new CategoryDTO(1, "name", "description", "image","icon")));
  }

  @Test
  public void findAllTest() {
    service.findAll();
    Mockito.verify(repository).findAll();
  }

  @Test
  public void findByNameTest() {
    Category found = createTestCategory(1);
    Mockito.doCallRealMethod().when(mapper).mapToDTO(found);
    Mockito.when(repository.findByTitle("HAMSTER")).thenReturn(Optional.of(found));
    service.findByName("Hamster");
    Mockito.verify(repository).findByTitle("HAMSTER");
  }

  @Test
  public void findByName_exceptionTest() {
    Mockito.when(repository.findByTitle("HAMSTER")).thenReturn(Optional.empty());
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findByName("Hamster"));
    Mockito.verify(repository).findByTitle("HAMSTER");
  }

  @Test
  public void findById_exceptionTest() {
    Mockito.when(repository.findById(1)).thenReturn(Optional.empty());
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findById(1));
  }

  @Test
  public void findById_successTest() {
    Mockito.when(repository.findById(1)).thenReturn(Optional.of(this.createTestCategory(1)));
    Category actual = service.findById(1);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Hamster", actual.getTitle());
    Assertions.assertEquals("guarderia de roedores", actual.getDescription());
    Assertions.assertEquals("url.com", actual.getImage());
  }

  @Test
  public void deleteById_exceptionTest() {
    Mockito.when(repository.findById(1)).thenReturn(Optional.empty());
    Assertions.assertThrows(ResourceNotFoundException.class, () -> service.deleteById(1));
  }

  @Test
  public void deleteById_successTest() {
    Mockito.when(repository.findById(1)).thenReturn(Optional.of(this.createTestCategory(1)));
    String actual = service.deleteById(1);

    Assertions.assertEquals("Se elimino exitosamente la categoría de id: 1", actual);
  }

    private Category createTestCategory(Integer id) {
        Category expectedCategory = new Category();
        expectedCategory.setId(id);
        expectedCategory.setTitle("Hamster");
        expectedCategory.setDescription("guarderia de roedores");
        expectedCategory.setImage("url.com");
        return expectedCategory;
    }
}
