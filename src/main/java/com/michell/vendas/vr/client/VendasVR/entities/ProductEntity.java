package com.michell.vendas.vr.client.VendasVR.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_sequence")
    @SequenceGenerator(name="produto_sequence", sequenceName = "seq_produto_id", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "descricao")
    private String description;

    @Column(name = "preco")
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<ProductItemEntity> productItens;


}
