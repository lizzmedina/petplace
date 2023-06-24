package com.example.demo.service;

import com.example.demo.DTO.*;
import com.example.demo.entity.*;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.CityMapper;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.PetDayCareRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
public class BookingTest {

    @Mock
    BookingRepository bookingRepository;

    @Mock
    PetDayCareRepository petDayCareRepository;
    @Mock
    private CityMapper cityMapper;
    @Mock
    UserRepository userRepository;

    @Mock
    CityRepository cityRepository;

    @Mock
    RoleService roleService;
    @Mock
    CategoryService categoryService;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    BookingService bookingService;

    @InjectMocks
    PetDayCareService petDayCareService;

    @InjectMocks
    UserService userService;
    @InjectMocks
    CityService cityService;

    @Test
    @DisplayName("Esta prueba valida la creación de una reserva nulo")
    public void save_nullTest(){
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bookingService.save(null), "La reserva no puede ser nulo");
        Mockito.verify(bookingRepository,Mockito.times(0)).save(null);
    }

    @Test
    @DisplayName("Esta prueba valida que el usuario o el hotel no se encuentran")
    public void save_notFoundUserTest(){

        BookingDTO expectedBooking = this.createTestBooking(1);

        Assertions.assertThrows(RuntimeException.class,
                () -> bookingService.save(expectedBooking), "El usuario o hotel no se encuentran registrados");
        Mockito.verify(bookingRepository,Mockito.times(0)).save(null);
    }


//    @Test
//    @DisplayName("Esta prueba valida que las fechas no estan disponibles")
//    public void save_notAvailableDate(){
//
//        BookingDTO expectedBooking = this.createTestBooking(1);
//        UserDTO expectedUser = this.createTestUser(1);
//
//
//
//        City expectedCity = new City();
//        expectedCity.setId(1);
//        expectedCity.setName("Ciudad creada");
//
//        CityDTO dto = new CityDTO("Ciudad creada");
//        Mockito.when(cityRepository.save(cityMapper.mapToEntity(dto))).thenReturn(expectedCity);
//
//        PetDayCare expectedPetDayCare = this.createTestPetDayCare(1);
//
//        PetDayCareDTO petDayCareDTO = new PetDayCareDTO(
//                expectedPetDayCare.getName(),
//                expectedPetDayCare.getType(),
//                expectedPetDayCare.getCapacity(),
//                dto,
//                expectedPetDayCare.getAddress(),
//               expectedPetDayCare.getDetail(),
//               expectedPetDayCare.getImages(),
//               expectedPetDayCare.getCharacteristics(),
//               expectedPetDayCare.getBasicPrice(),
//               expectedPetDayCare.getHouseRules(),
//               expectedPetDayCare.getHealthAndSecurity(),
//               expectedPetDayCare.getCancellationPolicy()
//        );
//
//
//        List<String> checkInOut =new ArrayList<>();
//        checkInOut.add("2023-10-11");
//        checkInOut.add("2023-10-15");
//
//
//        userService.save(expectedUser);
//        petDayCareService.save(petDayCareDTO);
//        bookingService.save(expectedBooking);
//
//        BookingDTO find = new BookingDTO(checkInOut, 0.0, 1, 1, "petname");
//
//
//        Assertions.assertThrows(RuntimeException.class,
//                () -> bookingService.save(find), "las fechas a reservar no estan disponibles en ese ajolamiento pues ya se encuentra reservado");
//        Mockito.verify(bookingRepository,Mockito.times(0)).save(null);
//    }

    private BookingDTO createTestBooking(Integer id) {

        List<String> checkInOut =new ArrayList<>();
        checkInOut.add("2023-10-11");
        checkInOut.add("2023-10-15");
        System.out.println(checkInOut);

        BookingDTO expectedBooking = new BookingDTO();
        expectedBooking.setIdBooking(1);
        expectedBooking.setCheckInCheckOut(checkInOut);
        expectedBooking.setPetDayCareId(1);
        expectedBooking.setUserId(1);

        return expectedBooking;
    }


    private  UserDTO createTestUser(Integer id){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        return userDTO;

    }


    private PetDayCare createTestPetDayCare(Integer id){
        //Given
        City expectedCity = new City();
        expectedCity.setId(1);
        expectedCity.setName("Ciudad creada");

        CityDTO dto = new CityDTO("Ciudad creada");
        Mockito.when(cityRepository.save(cityMapper.mapToEntity(dto))).thenReturn(expectedCity);
        PetDayCareDTO petDayCareDTO = new PetDayCareDTO();
//        petDayCareDTO.setType(new Category("canarios","Expertos en canarios",null));
//        CategoryDTO categoryDTO = this.categoryService.findByName(petDayCareDTO.getType().getTitle());

        PetDayCare expectedPetDayCare = new PetDayCare();
        expectedPetDayCare.setId(50);
        expectedPetDayCare.setName("Prueba hotel");
        expectedPetDayCare.setType(new Category("canarios","Expertos en canarios",null, null));
        expectedPetDayCare.setCapacity(30);
        expectedPetDayCare.setCity(expectedCity);
        expectedPetDayCare.setAddress("Ensayo");
        expectedPetDayCare.setImages(null);
        expectedPetDayCare.setCharacteristics(null);
        expectedPetDayCare.setBasicPrice(50.000);
        expectedPetDayCare.setHouseRules(null);
        expectedPetDayCare.setHealthAndSecurity(null);
        expectedPetDayCare.setCancellationPolicy(null);

        //When
        Mockito.when(petDayCareRepository.save(expectedPetDayCare)).thenReturn(expectedPetDayCare);//Cuando le dé guardar con el hotel anterior, retorneme el mismo hotel
        PetDayCare actualPetDayCare = petDayCareRepository.save(expectedPetDayCare);

        return expectedPetDayCare;

    }

    @Test
    @DisplayName("Test para un idUser nulo")
    public void bookingsUserNullIdTest(){
        //given: Settear el escenario de pruebas, para probar con esos datos.

        //when: Cuando el id sea nulo, verificamos que se lanzó la exception
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bookingService.bookingsUserId(null), "El id no puede ser nulo");//Los assert son verificar. Se verifica que cuando se lance un save con hotel nulo, se debe verificar que lance la excepción que to quiero que lance

        //then: Mockito por favor verifique que al repositorio que es el mock, lo llamaron con un parametro null
        Mockito.verify(bookingRepository,Mockito.times(0)).save(null);//Verifique que nunca se llamó al reposrotorio porque el parametro es nulo.

    }

    /*@Test
    @DisplayName("Esta prueba valida la edición de un id invalido")
    public void bookingsUserinvalidIdTest() {
        //given: Settear el escenario de pruebas, para probar con esos datos.

        //when: Cuando el id sea nulo, verificamos que se lanzó la exception
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> bookingService.bookingsUserId(100), "Las reservas no fueron encontradas");//Los assert son verificar. Se verifica que cuando se lance un save con hotel nulo, se debe verificar que lance la excepción que to quiero que lance

        //then: Mockito por favor verifique que al repositorio que es el mock, lo llamaron con un parametro null
        Mockito.verify(bookingRepository,Mockito.times(0)).findByUserId(100);//Verifique que nunca se llamó al reposrotorio porque el parametro es nulo.

    }*/
}
