package com.demo.customer.ejb;

import com.demo.customer.util.dto.CustomerRequestDTO;
import com.demo.customer.util.dto.CustomerResponseDTO;

import java.util.Collection;

public interface CustomerService {
    CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO);
    Collection<CustomerResponseDTO> findAllCustomer();
}
