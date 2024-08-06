package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.exceptions.CustomerAlreadyExistException;
import com.michell.vendas.vr.client.VendasVR.exceptions.CustomerNotFoundException;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Scope("prototype")
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerSpecifications customerSpecifications;

    public void saveCustomer(CustomerDTO dto){
        checkCustomerExist(dto.getCustomerName());
        CustomerEntity customer = createCustomerEntity(dto);
        customerRepository.saveAndFlush(customer);
    }
    private void checkCustomerExist(String customerName){
        Specification<CustomerEntity> hasCustomer = customerSpecifications.hasCustomerName(customerName);
        Optional<CustomerEntity> optCustomer = customerRepository.findOne(hasCustomer);
        if(optCustomer.isPresent())
            throw new CustomerAlreadyExistException(String.format("Cliente (%s) já existe", customerName));
    }

    private CustomerEntity createCustomerEntity(CustomerDTO dto){
        CustomerEntity entity = new CustomerEntity();
        entity.setPurchaseLimit(dto.getPurchaseLimit());
        entity.setCustomerName(dto.getCustomerName());
        entity.setClosingDateAt(dto.getClosingDateAt());
        return entity;

    }

    public List<CustomerEntity> findAllUser(){
        return customerRepository.findAll();
    }

    public CustomerEntity findById(Long customerId){
        Optional<CustomerEntity> optCustomerEntity = customerRepository.findById(customerId);
        if(!optCustomerEntity.isPresent())
            throw new CustomerNotFoundException(customerId);
        return optCustomerEntity.get();
    }


    public void deleteCustomer(Long id){
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    public void updateCustomer(CustomerEntity entity, CustomerDTO dto ) {
        entity.setClosingDateAt(dto.getClosingDateAt());
        entity.setCustomerName(dto.getCustomerName());
        entity.setPurchaseLimit(dto.getPurchaseLimit());
        customerRepository.saveAndFlush(entity);
    }
}
