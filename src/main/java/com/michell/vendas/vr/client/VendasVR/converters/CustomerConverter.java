package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    public CustomerEntity convert(CustomerEntity entity, CustomerDTO dto){
        entity.setId(dto.getId());
        entity.setCustomerName(dto.getCustomerName());
        entity.setClosingDateAt(dto.getClosingDateAt());
        entity.setPurchaseLimit(dto.getPurchaseLimit());
        return entity;
    }

    public CustomerDTO convert(CustomerEntity entity){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setClosingDateAt(entity.getClosingDateAt());
        dto.setPurchaseLimit(entity.getPurchaseLimit());
        return dto;
    }

    public List<CustomerDTO> convert(List<CustomerEntity> customersEntities){
        List<CustomerDTO> customersResponseDTO = new ArrayList<>();
        for (CustomerEntity customerEntity : customersEntities){
            customersResponseDTO.add(convert(customerEntity));
        }
        return customersResponseDTO;
    }
}
