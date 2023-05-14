package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        if (customer != null) {
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