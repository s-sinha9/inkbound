package com.inkbound.controller;

import com.inkbound.dto.CreateCustomerRequest;
import com.inkbound.dto.CustomerResponse;
import com.inkbound.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestParam CreateCustomerRequest customerRequest){
        log.info("CustomerController.createCustomer");
        CustomerResponse response = this.customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable UUID id) {
        log.info("CustomerController.getCustomerById");
        CustomerResponse response = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

}
