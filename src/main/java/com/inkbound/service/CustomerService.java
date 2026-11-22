package com.inkbound.service;

import com.inkbound.dto.CreateCustomerRequest;
import com.inkbound.dto.CustomerResponse;
import com.inkbound.exception.CustomerNotFoundException;
import com.inkbound.exception.DuplicateResourceException;
import com.inkbound.model.Customer;
import com.inkbound.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository=customerRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public CustomerResponse createCustomer(CreateCustomerRequest customerRequest){
        log.info("CustomerService.createCustomer");
        if (this.customerRepository.existsByEmail(customerRequest.getEmail())){
            throw new DuplicateResourceException(
                    "Customer already registered with this email" + customerRequest.getEmail()
            );        }
        Customer request = getCustomerFromRequest(customerRequest);
        customerRepository.save(request);
        return getResponseFromCustomer(request);
    }

    public CustomerResponse getCustomerById(UUID id){
        log.info("CustomerService.getCustomerById");
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotFoundException(id));
        return getResponseFromCustomer(customer);
    }

    private Customer getCustomerFromRequest(CreateCustomerRequest request){
        Customer res = new Customer();
        res.setId(UUID.randomUUID());
        res.setName(request.getName());
        res.setEmail(request.getEmail());
        res.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        return res;
    }

    private CustomerResponse getResponseFromCustomer(Customer customer){
        CustomerResponse res = new CustomerResponse();
        res.setId(customer.getId());
        res.setName(customer.getName());
        res.setEmail(customer.getEmail());
        return res;
    }

}
