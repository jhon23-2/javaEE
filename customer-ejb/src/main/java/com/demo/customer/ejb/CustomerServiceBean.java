package com.demo.customer.ejb;

import com.demo.customer.util.dto.CustomerRequestDTO;
import com.demo.customer.util.dto.CustomerResponseDTO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Stateless
public class CustomerServiceBean implements CustomerService{

    @PersistenceContext(unitName = "customerPU")
    private EntityManager entityManager;

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {
        return null;
    }

    @Override
    public Collection<CustomerResponseDTO> findAllCustomer() {
        return null;
    }
}
