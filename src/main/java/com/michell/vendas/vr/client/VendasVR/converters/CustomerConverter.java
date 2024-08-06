package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerStoreDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.CustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    public CustomerEntity convert(CustomerEntity entity, CustomerStoreDTO customerStoreDTO){
        CustomerDTO dto = customerStoreDTO.getCustomer();
        entity.setId(dto.getId());
        entity.setId(dto.getId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setClosingDateAt(dto.getClosingDateAt());
        entity.setPurchaseLimit(dto.getPurchaseLimit());
        return entity;
    }

    public CustomerResponseDTO convert(CustomerEntity entity){
        CustomerResponseDTO dto = new CustomerResponseDTO();
        dto.setId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setClosingDateAt(entity.getClosingDateAt());
        dto.setPurchaseLimit(entity.getPurchaseLimit());
        return dto;
    }

    public List<CustomerResponseDTO> convert(List<CustomerEntity> customersEntities){
        List<CustomerResponseDTO> customersResponseDTO = new ArrayList<>();
        for (CustomerEntity customerEntity : customersEntities){
            customersResponseDTO.add(convert(customerEntity));
        }
        return customersResponseDTO;
    }
}
