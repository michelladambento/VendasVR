package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.request.CustomerRequestDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.response.CustomerResponseDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerConverter {

    public CustomerEntity toCustomerEntity(CustomerRequestDTO customerRequestDTO){
        return CustomerEntity.builder()
                .id(customerRequestDTO.getId())
                .customerName(customerRequestDTO.getCustomerName())
                .purchaseLimit(customerRequestDTO.getPurchaseLimit())
                .closingDateAt(customerRequestDTO.getClosingDateAt())
                .build();
    }

    public CustomerResponseDTO toCustomerResponseDTO(CustomerEntity customerEntity){
        return CustomerResponseDTO.builder()
                .id(customerEntity.getId())
                .customerName(customerEntity.getCustomerName())
                .purchaseLimit(customerEntity.getPurchaseLimit())
                .closingDateAt(customerEntity.getClosingDateAt())
                .build();
    }

    public List<CustomerResponseDTO> toCustomerResponseDTO(List<CustomerEntity> customersEntities){
        List<CustomerResponseDTO> customersResponseDTO = new ArrayList<>();
        for (CustomerEntity customerEntity : customersEntities){
            customersResponseDTO.add(toCustomerResponseDTO(customerEntity));
        }
        return customersResponseDTO;
    }
}
