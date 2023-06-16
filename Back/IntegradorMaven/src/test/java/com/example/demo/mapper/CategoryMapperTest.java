package com.example.demo.mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CategoryMapperTest {
  CategoryMapper categoryMapper = new CategoryMapper();

  @Test
  @DisplayName(
      "Verifica que una entidad Category se mapee correctamente a un CityDTO sin PetDayCare asociados")
  public void mapToDto_success() {
    Category category = new Category("Perros", "Descripcion", "imagen");
    category.setId(1);

    CategoryDTO actual = categoryMapper.mapToDTO(category);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Perros", actual.getTitle());
    Assertions.assertEquals("Descripcion", actual.getDescription());
    Assertions.assertEquals("imagen", actual.getImage());
  }

  @Test
  @DisplayName("Verifica que un CityDTO se mapee correctamente a un City sin PetDayCare asociados")
  public void mapToEntity_success_withoutPdc() {
    CategoryDTO dto = new CategoryDTO(2, "Gatos", "Descripcion", "imagen");

    Category actual = categoryMapper.mapToEntity(dto);

    Assertions.assertEquals(2, actual.getId());
    Assertions.assertEquals("Gatos", actual.getTitle());
    Assertions.assertEquals("Descripcion", actual.getDescription());
    Assertions.assertEquals("imagen", actual.getImage());
  }

  @Test
  @DisplayName("Verifica que el mapeo de un null no lance un NPE")
  public void mapToDto_null() {
    Assertions.assertNull(categoryMapper.mapToEntity(null));
    Assertions.assertNull(categoryMapper.mapToDTO(null));
  }
}
