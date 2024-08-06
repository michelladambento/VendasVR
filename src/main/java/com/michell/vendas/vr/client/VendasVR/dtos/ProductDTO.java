package com.michell.vendas.vr.client.VendasVR.dtos;

import com.michell.vendas.vr.client.VendasVR.entities.ProductItemEntity;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Generated
public class ProductDTO {

    private Long id;

    private String description;

    private Double price;

}
