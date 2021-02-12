package dev.pirokiko.commerceshop.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pirokiko.commerceshop.customer.dto.CustomerDto;
import dev.pirokiko.commerceshop.customer.entity.Customer;
import dev.pirokiko.commerceshop.customer.repository.CustomerRepository;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerCRUDController {
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    public CustomerCRUDController(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public List<Customer> list() {
        return this.customerRepository.findAll();
    }

    @PostMapping("/")
    public Customer create(@RequestBody CustomerDto customerDto) {
        Customer customer = objectMapper.convertValue(customerDto, Customer.class);
        return this.customerRepository.save(customer);
    }

    @GetMapping("/{customerId}")
    public Customer read(@PathVariable Long customerId) throws NotFoundException {
        return this.customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("No customer found for id: " + customerId));
    }

    @PutMapping("/{customerId}")
    public Customer update(@RequestBody Customer customer, @PathVariable Long customerId) {
        if (customerId < 1 || !customer.getId().equals(customerId)) {
            throw new IllegalArgumentException("Customer must have an id for it to be updated");
        }
        return this.customerRepository.save(customer);
    }

    @DeleteMapping("/{customerId}")
    public void delete(@RequestBody Customer customer, @PathVariable Long customerId) {
        if (customerId < 1 || !customer.getId().equals(customerId)) {
            throw new IllegalArgumentException("Customer must have an id for it to be updated");
        }
        this.customerRepository.delete(customer);
    }
}
