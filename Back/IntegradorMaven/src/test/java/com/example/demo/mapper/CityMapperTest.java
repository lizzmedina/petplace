package com.example.demo.mapper;

import com.example.demo.DTO.CityDTO;
import com.example.demo.DTO.PetDayCareDTO;
import com.example.demo.entity.City;
import com.example.demo.entity.PetDayCare;
import com.example.demo.utils.JsonHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CityMapperTest {
  static List<PetDayCare> petDayCareList;
  CityMapper cityMapper = new CityMapper();

  @BeforeAll
  public static void setup() {
    petDayCareList = JsonHelper.readJsonFromFile("petdaycare_data.json", new TypeReference<>() {});
  }

  @Test
  @DisplayName(
      "Verifica que una entidad City se mapee correctamente a un CityDTO sin PetDayCare asociados")
  public void mapToDto_success_withoutPdc() {
    City entity = new City();
    entity.setId(1);
    entity.setName("Cali");
    entity.setPetDayCareSet(Collections.emptySet());

    CityDTO actual = cityMapper.mapToDto(entity);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Cali", actual.getName());
    Assertions.assertTrue(actual.getPetDayCareDTOSet().isEmpty());
  }

  @Test
  @DisplayName(
      "Verifica que una entidad City se mapee correctamente a un CityDTO con PetDayCare asociados")
  public void mapToDto_success_withPdc() {
    City entity = new City();
    entity.setId(1);
    entity.setName("Cali");
    entity.setPetDayCareSet(
        petDayCareList.stream()
            .filter(pdc -> pdc.getCity().getName().equals(entity.getName()))
            .collect(Collectors.toSet()));

    CityDTO actual = cityMapper.mapToDto(entity);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Cali", actual.getName());
    Assertions.assertEquals(6, actual.getPetDayCareDTOSet().size());

    boolean containsAll =
        actual.getPetDayCareDTOSet().stream()
            .map(PetDayCareDTO::getId)
            .collect(Collectors.toSet())
            .containsAll(
                entity.getPetDayCareSet().stream()
                    .map(PetDayCare::getId)
                    .collect(Collectors.toSet()));

    Assertions.assertTrue(containsAll);
  }

  @Test
  @DisplayName("Verifica que un CityDTO se mapee correctamente a un City sin PetDayCare asociados")
  public void mapToEntity_success_withoutPdc() {
    CityDTO dto = new CityDTO();
    dto.setId(1);
    dto.setName("Barranquilla");
    dto.setPetDayCareDTOSet(Collections.emptySet());

    City actual = cityMapper.mapToEntity(dto);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Barranquilla", actual.getName());
    Assertions.assertNull(actual.getPetDayCareSet());
  }

  @Test
  @DisplayName("Verifica que un CityDTO se mapee correctamente a un City con PetDayCare asociados")
  public void mapToEntity_success_withPdc() {

    CityDTO dto = new CityDTO();
    dto.setId(1);
    dto.setName("Cali");
    dto.setPetDayCareDTOSet(Set.of(Mockito.mock(PetDayCareDTO.class)));

    City actual = cityMapper.mapToEntity(dto);

    Assertions.assertEquals(1, actual.getId());
    Assertions.assertEquals("Cali", actual.getName());
    Assertions.assertNull(actual.getPetDayCareSet());
  }

  @Test
  @DisplayName("Verifica que el mapeo de un null no lance un NPE")
  public void map_null() {
    Assertions.assertNull(cityMapper.mapToDto(null));
    Assertions.assertNull(cityMapper.mapToEntity(null));
  }
}
