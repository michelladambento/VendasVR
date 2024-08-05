package com.michell.vendas.vr.client.VendasVR.services;

import com.michell.vendas.vr.client.VendasVR.converters.CustomerConverter;
import com.michell.vendas.vr.client.VendasVR.dtos.request.CustomerRequestDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.CustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    public CustomerEntity saveCustomer(CustomerEntity customer){
        return customerRepository.saveAndFlush(customer);
    }

    public CustomerResponseDTO saveCustomer(CustomerRequestDTO customerRequestDTO){
        try {
            CustomerEntity customerEntity = saveCustomer(customerConverter.toCustomerEntity(customerRequestDTO));
            return customerConverter.toCustomerResponseDTO(customerEntity);
        }catch (Exception e){
            throw new RuntimeException("Error to save date from user");
        }
    }

    public List<CustomerResponseDTO> findAllUser(){
        List<CustomerEntity> customersEntity = customerRepository.findAll();
        return customerConverter.toCustomerResponseDTO(customersEntity);
    }

    public void deleteCustomer(Long id){
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente não encontrado com (id): " + id);
        }

    }
}
