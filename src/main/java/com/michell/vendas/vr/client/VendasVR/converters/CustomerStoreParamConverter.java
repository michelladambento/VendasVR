package com.michell.vendas.vr.client.VendasVR.converters;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerParam;
import com.michell.vendas.vr.client.VendasVR.dtos.CustomerStoreDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerStoreParamConverter {

    public CustomerParam convert(CustomerStoreDTO request){
        if(request == null) return null;
        CustomerDTO customerDTO = request.getCustomer();
        CustomerParam param = new CustomerParam();
        param.setId(customerDTO.getId());
        param.setCustomerName(customerDTO.getCustomerName());
        param.setClosingDateAt(customerDTO.getClosingDateAt());
        param.setPurchaseLimit(customerDTO.getPurchaseLimit());
        return param;
    }
}
