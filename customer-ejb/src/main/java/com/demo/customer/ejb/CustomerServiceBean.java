package com.demo.customer.ejb;

import com.demo.customer.util.dto.CustomerRequestDTO;
import com.demo.customer.util.dto.CustomerResponseDTO;
import com.demo.customer.util.model.CustomerModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class CustomerServiceBean implements CustomerService{

    @PersistenceContext(unitName = "customerPU")
    private EntityManager entityManager;

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {

        Optional<CustomerModel> model = Optional.ofNullable(this.entityManager.merge(new CustomerModel(
                customerRequestDTO.getName(),
                customerRequestDTO.getLastName()))
        );

        if (!model.isPresent()) return new CustomerResponseDTO();

        CustomerModel customerCreated = model.get();
        CustomerResponseDTO response = new CustomerResponseDTO();

        response.setId(String.valueOf(customerCreated.getId()));
        response.setName(customerCreated.getName());
        response.setLastName(customerCreated.getLastName());

        return response;
    }

    @Override
    public Collection<CustomerResponseDTO> findAllCustomer() {
        List<CustomerModel> models = entityManager
                .createQuery("SELECT c FROM CustomerModel c", CustomerModel.class)
                .getResultList();

        return models.stream()
                .map(m -> {
                    CustomerResponseDTO dto = new CustomerResponseDTO();
                    dto.setId(m.getId() == null ? null : String.valueOf(m.getId()));
                    dto.setName(m.getName());
                    dto.setLastName(m.getLastName());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
