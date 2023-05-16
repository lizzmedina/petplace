package com.example.demo.service;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Pet;
import com.example.demo.entity.PetDayCare;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    private PetRepository petRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }



    public Customer save(Customer customer) {
        if (customer != null) {

         customer.getPets().forEach((pet) -> {Pet newPet = new Pet(
                    pet.getId(),
                    pet.getPetName(),
                    pet.getPetType(),
                    pet.getPetSize()
                );
                petRepository.save(newPet);
            });


            Customer newCustomer = new Customer(
                    customer.getId(),
                    customer.getName(),
                    customer.getLastName(),
                    customer.getEmail(),
                    customer.getPassword(),
                    customer.getCellPhone(),
                    customer.getAddress(),
                    customer.getType(),
                    customer.getPets()
            );

            return customerRepository.save(customer);
        } else {
            return null;
        }
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();

    }

    public void delete(Integer id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            customerRepository.delete(customerOpt.get());
        }
        throw new ResourceNotFoundException("No existe un cliente con id: " + id);
    }




}