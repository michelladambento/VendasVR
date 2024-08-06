package com.michell.vendas.vr.client.VendasVR.dtos.response;

import com.michell.vendas.vr.client.VendasVR.dtos.CustomerDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ProductDTO;
import com.michell.vendas.vr.client.VendasVR.dtos.ResponseDTO;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
public class RetrieveAllProductsDTO extends ResponseDTO{

    private List<ProductDTO> products;

    public RetrieveAllProductsDTO(boolean success, String detail){
        super(success, detail);
    }


}
