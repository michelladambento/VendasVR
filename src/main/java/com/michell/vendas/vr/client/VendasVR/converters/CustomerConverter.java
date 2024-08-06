package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.entities.CustomerEntity;

import java.util.ArrayList;
import java.util.List;

public interface CustomerConverter {

    CustomerEntity convert(CustomerEntity entity, CustomerDTO dto);

    CustomerDTO converter(CustomerEntity entity);

    List<CustomerDTO> converterToListDto(List<CustomerEntity> customersEntities);

    CustomerEntity converter(CustomerDTO dto);

   List<CustomerEntity> converter(List<CustomerDTO> customersDTOs);

}
