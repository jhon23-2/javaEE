package com.demo.customer.ejb;

import com.demo.customer.util.dto.CustomerRequestDTO;
import com.demo.customer.util.dto.CustomerResponseDTO;
import com.demo.customer.util.model.CustomerModel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class CustomerServiceBean implements CustomerService{

    @PersistenceContext(unitName = "customerPU")
    private EntityManager entityManager;

    private final Logger log = Logger.getLogger(CustomerServiceBean.class.getName());

    @Override
    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO) {

        Optional<CustomerModel> model = Optional.ofNullable(this.entityManager.merge(new CustomerModel(
                customerRequestDTO.getName(),
                customerRequestDTO.getLastName()))
        );

        if (!model.isPresent()) return new CustomerResponseDTO();

        CustomerModel customerCreated = model.get();
        LocalDateTime dateCreation = LocalDateTime.now();
        log.info("-------- User saved successfully id: " + customerCreated.getId()  + " Date Creation: " +dateCreation.toLocalDate().toString());
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

        log.info("All users Fetched: " + models.size());

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
