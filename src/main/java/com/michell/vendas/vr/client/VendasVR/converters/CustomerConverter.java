package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductItemDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
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

    public CustomerDTO converter(CustomerEntity entity){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setCustomerName(entity.getCustomerName());
        dto.setClosingDateAt(entity.getClosingDateAt());
        dto.setPurchaseLimit(entity.getPurchaseLimit());
        return dto;
    }

    public List<CustomerDTO> converterToListDto(List<CustomerEntity> customersEntities){
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for (CustomerEntity customerEntity : customersEntities){
            customersDTO.add(converter(customerEntity));
        }
        return customersDTO;
    }

    //correto pro purchaser
    public CustomerEntity converter(CustomerDTO dto){
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerName(dto.getCustomerName());
        entity.setClosingDateAt(dto.getClosingDateAt());
        entity.setPurchaseLimit(dto.getPurchaseLimit());
        return entity;
    }

    public List<CustomerEntity> converter(List<CustomerDTO> customersDTOs){
        List<CustomerEntity> customersEntity = new ArrayList<>();
        for (CustomerDTO customerDTO : customersDTOs){
            customersEntity.add(converter(customerDTO));
        }
        return customersEntity;
    }


}
