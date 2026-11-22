package com.inkbound.service;

import com.inkbound.model.Customer;
import com.inkbound.repository.CustomerRepository;
import com.inkbound.security.CustomerUserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerUserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Customer not found: " + email
                ));

        return new CustomerUserDetails(customer);
    }
}
